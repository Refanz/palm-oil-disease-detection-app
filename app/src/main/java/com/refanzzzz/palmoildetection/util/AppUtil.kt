package com.refanzzzz.palmoildetection.util

import android.net.Uri
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object AppUtil {
    fun LocalDateTime.toFormattedDateString(): String {
        val indonesianLocale = Locale("id", "ID")
        val dayOfWeek = this.dayOfWeek.getDisplayName(TextStyle.FULL, indonesianLocale)

        val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", indonesianLocale)
        val formattedDate = this.format(dateFormatter)

        return "$dayOfWeek, $formattedDate"
    }

    fun Float.toFormattedFloat2Decimal(): String {
        return String.format(Locale("id", "ID"), "%.2f", this)
    }

    fun Uri.getImageFile(): File? {
        return File(this.path!!)
    }
}