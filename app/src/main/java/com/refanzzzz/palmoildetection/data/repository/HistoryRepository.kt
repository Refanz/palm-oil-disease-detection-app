package com.refanzzzz.palmoildetection.data.repository

import com.refanzzzz.palmoildetection.data.model.HistoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDateTime
import javax.inject.Inject

class HistoryRepository @Inject constructor() {
    fun getDummyHistory(): Flow<List<HistoryItem>> = flow {
        val histories = listOf<HistoryItem>(
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
            ),
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
            )
        ).toList()
        emit(histories)
    }.flowOn(Dispatchers.IO)
}