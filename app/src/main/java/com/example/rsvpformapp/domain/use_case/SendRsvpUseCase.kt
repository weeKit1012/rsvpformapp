package com.example.rsvpformapp.domain.use_case

import com.example.rsvpformapp.domain.model.RsvpObject
import com.example.rsvpformapp.domain.repository.FormRepository

class SendRsvpUseCase(
        private val repository: FormRepository
)
{
    suspend operator fun invoke(
            firstName: String,
            lastName: String,
            contactNo: String,
            email: String
    ): Result<String>
    {
        val rsvpObject = RsvpObject(
                firstName = firstName,
                lastName = lastName,
                contactNo = contactNo,
                email = email
        )

        return repository.sendRsvp(rsvpObject)
    }
}