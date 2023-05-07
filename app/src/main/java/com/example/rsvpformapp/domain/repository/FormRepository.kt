package com.example.rsvpformapp.domain.repository

import com.example.rsvpformapp.domain.model.RsvpObject

interface FormRepository
{
    suspend fun sendRsvp(rsvpObject: RsvpObject): Result<String>
}