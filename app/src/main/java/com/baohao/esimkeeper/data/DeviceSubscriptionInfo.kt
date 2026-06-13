package com.baohao.esimkeeper.data

data class DeviceSubscriptionInfo(
    val carrierName: String,
    val phoneNumber: String,
    val countryIso: String,
    val isEmbedded: Boolean,
    val slotIndex: Int,
    val subscriptionId: Int,
) {
    val displayTitle: String
        get() = carrierName.ifBlank {
            if (isEmbedded) "本机 eSIM" else "本机 SIM"
        }
}
