package com.example.rsvpformapp.domain.model

data class RsvpObject(
        val firstName: String,
        val lastName: String,
        val contactNo: String,
        val email: String,
        val apiKey: String = "123456"
)
