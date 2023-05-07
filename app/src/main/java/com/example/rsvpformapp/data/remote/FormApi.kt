package com.example.rsvpformapp.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FormApi
{
    @GET("/interviewapi/AssessmentTestRSVP")
    suspend fun sendRsvp(
            @Query("ApiKey") apiKey: String,
            @Query("Email") email: String,
            @Query("LastName") lastName: String,
            @Query("ContactNo") contactNo: String,
            @Query("FirstName") firstName: String
    ): Response<ResponseBody>

    companion object {
        const val BASE_URL = "https://integration.micaresvc.com"
    }
}