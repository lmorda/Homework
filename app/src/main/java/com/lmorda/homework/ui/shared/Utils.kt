package com.lmorda.homework.ui.shared

import timber.log.Timber
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

const val MMMddyyyyhhmma = "MMM dd, yyyy hh:mm a"

object Utils {
    fun formatDateTime(dateTime: String?, locale: Locale): String {
        if (dateTime == null) return ""
        return try {
            OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .format(DateTimeFormatter.ofPattern(MMMddyyyyhhmma, locale))
        } catch (e: DateTimeParseException) {
            Timber.w(e)
            ""
        }
    }
}
