package com.baohao.esimkeeper.data

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import androidx.core.content.ContextCompat

object DeviceSubscriptionReader {
    val requiredPermissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_PHONE_NUMBERS,
    )

    fun hasPermission(context: Context): Boolean =
        requiredPermissions.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }

    @SuppressLint("MissingPermission")
    fun read(context: Context): List<DeviceSubscriptionInfo> {
        if (!hasPermission(context)) return emptyList()
        val manager = context.getSystemService(SubscriptionManager::class.java)
        val subscriptions = manager?.activeSubscriptionInfoList.orEmpty()
        return subscriptions
            .map { it.toDeviceSubscriptionInfo() }
            .sortedWith(compareByDescending<DeviceSubscriptionInfo> { it.isEmbedded }.thenBy { it.slotIndex })
    }
}

private fun SubscriptionInfo.toDeviceSubscriptionInfo(): DeviceSubscriptionInfo {
    val embedded = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) isEmbedded else false
    val display = displayName?.toString().orEmpty()
    val carrier = carrierName?.toString().orEmpty()
    return DeviceSubscriptionInfo(
        carrierName = display.ifBlank { carrier },
        phoneNumber = number.orEmpty(),
        countryIso = countryIso.orEmpty().uppercase(),
        isEmbedded = embedded,
        slotIndex = simSlotIndex,
        subscriptionId = subscriptionId,
    )
}
