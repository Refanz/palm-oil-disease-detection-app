package com.refanzzzz.palmoildetection.ui.screen.camera

import android.net.Uri

interface ImageCaptureCallback {
    fun onSuccess(imageUri: Uri)
    fun onFailed(message: String)
}