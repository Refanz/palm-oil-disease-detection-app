package com.refanzzzz.palmoildetection.util

enum class LabelPrediction {
    HEALTHY("Healthy"),
    FUNGAL_DISEASE("Fungal Disease"),
    MAGNESIUM_DEFICIENCY("Magnesium Deficiency"),
    SCALE_INSECT("Scale Insect");

    val desc: String

    constructor(desc: String) {
        this.desc = desc
    }

    companion object {
        fun getLabelByIndex(index: Int): String {
            return LabelPrediction.entries[index].desc
        }
    }
}