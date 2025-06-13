package com.lmorda.homework.ui.shared

import timber.log.Timber
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

const val MMMddyyyyhhmma = "MMM dd, yyyy hh:mm a"
const val MMMddyyyy = "MMM dd, yyyy"

object Utils {
    private fun formatDateTime(dateTime: String?, pattern: String, locale: Locale): String {
        if (dateTime == null) return ""
        return try {
            OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .format(DateTimeFormatter.ofPattern(pattern, locale))
        } catch (e: DateTimeParseException) {
            Timber.w(e)
            ""
        }
    }

    fun formatDateTimeMonthDayYearTime(dateTime: String?, locale: Locale): String =
        formatDateTime(dateTime, MMMddyyyyhhmma, locale)

    fun formatDateTimeMonthDayYear(dateTime: String?, locale: Locale): String =
        formatDateTime(dateTime, MMMddyyyy, locale)
}
