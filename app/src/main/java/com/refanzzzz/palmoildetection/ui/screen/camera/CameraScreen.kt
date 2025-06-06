package com.refanzzzz.palmoildetection.ui.screen.camera

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.refanzzzz.palmoildetection.navigation.Screen
import com.refanzzzz.palmoildetection.ui.component.ScanLoading

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    navController: NavController
) {
    val cameraViewModel = hiltViewModel<CameraViewModel>()
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val isLoading by cameraViewModel.isLoading.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    val executor = ContextCompat.getMainExecutor(context)

    if (cameraPermissionState.status.isGranted) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            if (isLoading) {
                ScanLoading()
            }

            CameraPreviewContent(
                modifier = Modifier.weight(1f),
                cameraViewModel = cameraViewModel,
                context = context
            )

            Button(
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(18.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    cameraViewModel.takePicture(
                        executor = executor,
                        contentResolver = contentResolver,
                        object : ImageCaptureCallback {
                            override fun onSuccess(imageUri: Uri) {
                                navController.navigate(Screen.Preview.createRoute(imageUri))
                            }

                            override fun onFailed(message: String) {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            ) {
                Text(
                    text = "Scan Now",
                    fontSize = 24.sp
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .wrapContentSize()
                .widthIn(max = 480.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                "Aplikasi ini membutuhkan akses kamera Anda untuk dapat memindai daun kelapa sawit dan mendeteksi potensi penyakit. Tanpa izin ini, fitur deteksi tidak dapat berfungsi."
            } else {
                "Aplikasi ini membutuhkan akses kamera untuk fitur deteksi penyakit daun kelapa sawit Anda."
            }

            Text(
                text = textToShow,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
            Spacer(Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    ),
                onClick = { cameraPermissionState.launchPermissionRequest() }
            ) {
                Text(
                    text = "Izin Kamera",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun CameraPreviewContent(
    context: Context,
    modifier: Modifier = Modifier,
    cameraViewModel: CameraViewModel,
) {
    val surfaceRequest by cameraViewModel.surfaceRequest.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        val processCameraProvider = ProcessCameraProvider.awaitInstance(context)
        cameraViewModel.bindToCamera(processCameraProvider, lifecycleOwner)
    }

    surfaceRequest?.let { request ->
        CameraXViewfinder(
            surfaceRequest = request,
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
        )
    }
}