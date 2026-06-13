package com.baohao.esimkeeper.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.ceil

data class ExpiryStatus(
    val totalDays: Long,
    val elapsedDays: Long,
    val remainingDays: Long,
    val progress: Float,
    val isWarning: Boolean,
    val isExpired: Boolean,
)

object ExpiryCalculator {
    fun expiryFromCycle(startDate: LocalDate, cycleDays: Int): LocalDate {
        require(cycleDays > 0) { "cycleDays must be positive" }
        return startDate.plusDays(cycleDays.toLong())
    }

    fun renewFrom(today: LocalDate, cycleDays: Int): Pair<LocalDate, LocalDate> {
        val nextExpiry = expiryFromCycle(today, cycleDays)
        return today to nextExpiry
    }

    fun status(
        startDate: LocalDate,
        expiryDate: LocalDate,
        today: LocalDate = LocalDate.now(),
    ): ExpiryStatus {
        val totalDays = ChronoUnit.DAYS.between(startDate, expiryDate).coerceAtLeast(1)
        val rawElapsedDays = ChronoUnit.DAYS.between(startDate, today)
        val elapsedDays = rawElapsedDays.coerceIn(0, totalDays)
        val remainingDays = ChronoUnit.DAYS.between(today, expiryDate)
        val isExpired = today.isAfter(expiryDate)
        val warningWindow = ceil(totalDays * 0.2).toLong().coerceAtLeast(1)
        val isWarning = !isExpired && remainingDays <= warningWindow
        val progress = if (isExpired) {
            0f
        } else {
            (remainingDays.toFloat() / totalDays.toFloat()).coerceIn(0f, 1f)
        }

        return ExpiryStatus(
            totalDays = totalDays,
            elapsedDays = elapsedDays,
            remainingDays = remainingDays,
            progress = progress,
            isWarning = isWarning,
            isExpired = isExpired,
        )
    }
}
