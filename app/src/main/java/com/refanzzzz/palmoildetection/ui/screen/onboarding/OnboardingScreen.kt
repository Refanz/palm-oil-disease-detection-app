package com.refanzzzz.palmoildetection.ui.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.refanzzzz.palmoildetection.data.model.OnboardingDataItem

@Composable
fun OnboardingScreen() {

    val onboardingViewModel = hiltViewModel<OnboardingViewModel>()
    val onboardingItems by onboardingViewModel.onboardingItems.collectAsStateWithLifecycle()


    val pagerState = rememberPagerState(pageCount = {
        3
    })

    HorizontalPager(state = pagerState) { page ->
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 36.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            if (onboardingItems != emptyList<OnboardingDataItem>()) {
                OnboardingItem(
                    imageResId = onboardingItems[page].imageResId,
                    title = onboardingItems[page].title,
                    desc = onboardingItems[page].desc
                )
            }

            OnboardingButton(onboardingViewModel, pagerState)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OnboardingItem(imageResId: Int, title: String, desc: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        GlideImage(
            model = imageResId,
            contentDescription = "onboarding",
            modifier = Modifier
                .size(200.dp)
        )

        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = desc,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(300.dp),
            fontSize = 20.sp
        )
    }
}

@Composable
fun OnboardingButton(
    onboardingViewModel: OnboardingViewModel,
    pagerState: PagerState
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterHorizontally
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            repeat(pagerState.pageCount) { iteration ->
                OnboardingButtonItem(pagerState.currentPage == iteration)
            }
        }

        if (pagerState.currentPage == pagerState.pageCount - 1) {
            Button(
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onboardingViewModel.setCompletedOnboarding()
                }
            ) {
                Text(
                    text = "Start Scanning",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun OnboardingButtonItem(isActive: Boolean = false) {

    val buttonColor = if (isActive) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surface
    }

    Box(
        modifier = Modifier
            .size(8.dp)
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(100.dp)
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}