package com.refanzzzz.palmoildetection.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.InputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale

object AppUtil {

    private const val FILENAME_FORMAT = "yyyMMdd_HHmmss"
    private val timestamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale("ID", "id")).format(
        Date()
    )

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

    private fun createCustomTempFile(context: Context): File {
        val filesDir = context.externalCacheDir
        return File.createTempFile(timestamp, ".jpg", filesDir)
    }

    fun Uri.getImageFile(context: Context): File {
        val myImage = createCustomTempFile(context)
        val inputStream = context.contentResolver.openInputStream(this) as InputStream
        val outputStream = myImage.outputStream()
        inputStream.copyTo(outputStream)
        return myImage
    }
}