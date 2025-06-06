package com.refanzzzz.palmoildetection.data.remote

import com.refanzzzz.palmoildetection.data.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


data class Test(
    val message: String
)

interface ApiService {
    @GET("/")
    suspend fun test(): Test

    @Multipart
    @POST("/api/v1/classify")
    suspend fun predict(
        @Part image: MultipartBody.Part
    ): PredictResponse


}