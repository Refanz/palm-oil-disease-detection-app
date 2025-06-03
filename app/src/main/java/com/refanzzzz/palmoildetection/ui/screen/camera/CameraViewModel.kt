package com.refanzzzz.palmoildetection.ui.screen.camera

import android.content.ContentResolver
import android.content.ContentValues
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor() : ViewModel() {
    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> = _surfaceRequest

    private val _imageCapture = MutableStateFlow<ImageCapture?>(null)
    val imageCapture: StateFlow<ImageCapture?> = _imageCapture

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newSurfaceRequest ->
            _surfaceRequest.value = newSurfaceRequest
        }
    }

    fun takePicture(
        executor: Executor,
        contentResolver: ContentResolver,
        callback: ImageCaptureCallback
    ) {

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
        }

        viewModelScope.launch(Dispatchers.IO) {
            val name =
                SimpleDateFormat(
                    FILENAME_FORMAT,
                    Locale("id", "ID")
                ).format(System.currentTimeMillis())
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.P) {
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
                }
            }

            val outputOptions = ImageCapture.OutputFileOptions.Builder(
                contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            ).build()

            _imageCapture.value!!.takePicture(
                outputOptions,
                executor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val message = "Photo captured succeeded: ${outputFileResults.savedUri}"
                        Log.d(TAG, message)

                        viewModelScope.launch(Dispatchers.Main) {
                            outputFileResults.let {
                                callback.onSuccess(it.savedUri!!)
                            }

                            _isLoading.value = false
                        }
                    }

                    override fun onError(exception: ImageCaptureException) {
                        callback.onFailed(exception.message!!)
                        Log.e(TAG, "Photo capture failed: ${exception.message}", exception)

                        viewModelScope.launch(Dispatchers.Main) {
                            _isLoading.value = false
                        }
                    }
                }
            )
        }
    }

    suspend fun bindToCamera(
        processCameraProvider: ProcessCameraProvider,
        lifecycleOwner: LifecycleOwner
    ) {
        _imageCapture.value = ImageCapture.Builder().build()

        processCameraProvider.bindToLifecycle(
            lifecycleOwner,
            CameraSelector.DEFAULT_BACK_CAMERA,
            cameraPreviewUseCase,
            imageCapture.value
        )

        try {
            awaitCancellation()
        } finally {
            processCameraProvider.unbindAll()
        }
    }

    companion object {
        private const val TAG = "CameraViewModel"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }
}