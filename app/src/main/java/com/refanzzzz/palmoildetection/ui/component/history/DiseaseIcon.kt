package com.refanzzzz.palmoildetection.ui.component.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.refanzzzz.palmoildetection.R
import com.refanzzzz.palmoildetection.util.LabelPrediction

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DiseaseIcon(
    modifier: Modifier = Modifier,
    disease: String = ""
) {
    val diseaseIconId: Int = when (disease) {
        LabelPrediction.HEALTHY.desc -> R.drawable.healthy
        LabelPrediction.SCALE_INSECT.desc -> R.drawable.scale_insect
        LabelPrediction.FUNGAL_DISEASE.desc -> R.drawable.fungal_disease
        LabelPrediction.MAGNESIUM_DEFICIENCY.desc -> R.drawable.magnesium_deficiency
        else -> R.drawable.healthy
    }

    GlideImage(
        model = diseaseIconId,
        contentDescription = disease,
        modifier = modifier
            .size(48.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(100.dp)
            )
    )
}

@Preview
@Composable
fun PreviewDiseaseIcon() {
    DiseaseIcon()
}