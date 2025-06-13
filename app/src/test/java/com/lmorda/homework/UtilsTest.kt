package com.lmorda.homework

import com.lmorda.homework.ui.shared.Utils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class UtilsTest {

    @Test
    fun `parse valid ISO offset date time string month day year time`() {
        val validDateTime = "2025-03-10T12:45:30+02:00"
        val parsedDateTime = Utils.formatDateTimeMonthDayYearTime(validDateTime, Locale.US)
        val expected = "Mar 10, 2025 12:45 PM"
        assertEquals(expected, parsedDateTime)
    }

    @Test
    fun `parse valid ISO offset date time string month day year`() {
        val validDateTime = "2025-03-10T12:45:30+02:00"
        val parsedDateTime = Utils.formatDateTimeMonthDayYear(validDateTime, Locale.US)
        val expected = "Mar 10, 2025"
        assertEquals(expected, parsedDateTime)
    }
}
