package com.example.rsvpformapp.data.repository

import com.example.rsvpformapp.data.remote.FormApi
import com.example.rsvpformapp.domain.model.RsvpObject
import com.example.rsvpformapp.domain.repository.FormRepository
import org.json.JSONObject

class FormRepositoryImpl(
        private val api: FormApi
) : FormRepository
{
    override suspend fun sendRsvp(rsvpObject: RsvpObject): Result<String>
    {
        try
        {
            val response = api.sendRsvp(
                    apiKey = rsvpObject.apiKey,
                    firstName = rsvpObject.firstName,
                    lastName = rsvpObject.lastName,
                    contactNo = rsvpObject.contactNo,
                    email = rsvpObject.email
            )

            return if (response.isSuccessful)
            {
                val responseMessage = response.body()?.string() ?: ""
                Result.success(responseMessage)
            }
            else
            {
                val errorJson = JSONObject(response.errorBody()?.string())
                Result.failure(Exception(errorJson.getString("title")))
            }
        }
        catch (e: Exception)
        {
            return Result.failure(e)
        }
    }
}