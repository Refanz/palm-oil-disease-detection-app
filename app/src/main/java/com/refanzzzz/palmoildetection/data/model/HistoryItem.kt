package com.refanzzzz.palmoildetection.data.model

import androidx.compose.runtime.Immutable
import java.time.LocalDateTime

@Immutable
data class HistoryItem(
    val name: String,
    val timestamp: LocalDateTime,
    val description: String,
    val disease: String
) {
    companion object {
        fun getDummyHistory(): List<HistoryItem> {
            return listOf<HistoryItem>(
                HistoryItem(
                    name = "Healthy",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "healthy"
                ),
                HistoryItem(
                    name = "Fungal Disease",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "fungal-disease"
                ),
                HistoryItem(
                    name = "Scale Insect",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "scale-insect"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                ),
                HistoryItem(
                    name = "Magnesium Deficiency",
                    timestamp = LocalDateTime.now(),
                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea taki",
                    disease = "magnesium-deficiency"
                )
            ).toList()
        }
    }
}
