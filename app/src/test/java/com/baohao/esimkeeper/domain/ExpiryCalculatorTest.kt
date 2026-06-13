package com.baohao.esimkeeper.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class ExpiryCalculatorTest {
    @Test
    fun cycleDaysCalculateExpiryDate() {
        val start = LocalDate.of(2026, 2, 23)

        val expiry = ExpiryCalculator.expiryFromCycle(start, 30)

        assertEquals(LocalDate.of(2026, 3, 25), expiry)
    }

    @Test
    fun directExpiryCalculatesProgressAndRemainingDays() {
        val status = ExpiryCalculator.status(
            startDate = LocalDate.of(2026, 2, 23),
            expiryDate = LocalDate.of(2026, 3, 25),
            today = LocalDate.of(2026, 3, 1),
        )

        assertEquals(30, status.totalDays)
        assertEquals(6, status.elapsedDays)
        assertEquals(24, status.remainingDays)
        assertEquals(0.8f, status.progress, 0.001f)
        assertFalse(status.isWarning)
        assertFalse(status.isExpired)
    }

    @Test
    fun lastTwentyPercentIsWarning() {
        val status = ExpiryCalculator.status(
            startDate = LocalDate.of(2026, 2, 23),
            expiryDate = LocalDate.of(2026, 3, 25),
            today = LocalDate.of(2026, 3, 20),
        )

        assertEquals(5, status.remainingDays)
        assertEquals(5f / 30f, status.progress, 0.001f)
        assertTrue(status.isWarning)
    }

    @Test
    fun afterExpiryIsExpired() {
        val status = ExpiryCalculator.status(
            startDate = LocalDate.of(2026, 2, 23),
            expiryDate = LocalDate.of(2026, 3, 25),
            today = LocalDate.of(2026, 3, 26),
        )

        assertTrue(status.isExpired)
        assertEquals(0f, status.progress, 0.001f)
    }

    @Test
    fun renewUsesTodayAsNewStartDate() {
        val (start, expiry) = ExpiryCalculator.renewFrom(
            today = LocalDate.of(2026, 6, 13),
            cycleDays = 90,
        )

        assertEquals(LocalDate.of(2026, 6, 13), start)
        assertEquals(LocalDate.of(2026, 9, 11), expiry)
    }

    @Test
    fun remainingProgressShrinksAsExpiryApproaches() {
        val early = ExpiryCalculator.status(
            startDate = LocalDate.of(2026, 2, 23),
            expiryDate = LocalDate.of(2026, 3, 25),
            today = LocalDate.of(2026, 3, 1),
        )
        val late = ExpiryCalculator.status(
            startDate = LocalDate.of(2026, 2, 23),
            expiryDate = LocalDate.of(2026, 3, 25),
            today = LocalDate.of(2026, 3, 20),
        )

        assertTrue(late.progress < early.progress)
    }
}
