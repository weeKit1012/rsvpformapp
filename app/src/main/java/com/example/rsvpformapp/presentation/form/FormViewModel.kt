package com.example.rsvpformapp.presentation.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsvpformapp.domain.use_case.SendRsvpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface FormScreenEvent
{
    data class OnEnterFirstName(val str: String) : FormScreenEvent
    data class OnEnterLastName(val str: String) : FormScreenEvent
    data class OnEnterContactNo(val str: String) : FormScreenEvent
    data class OnEnterEmail(val str: String) : FormScreenEvent
    object OnClickSubmitButton : FormScreenEvent
    data class OnToggleDialog(val isShown: Boolean) : FormScreenEvent
}

@HiltViewModel
class FormViewModel @Inject constructor(
        private val sendRsvpUseCase: SendRsvpUseCase
) : ViewModel()
{
    /* ===================================================== */
    /* Stored Properties */
    /* ===================================================== */

    var state by mutableStateOf(FormScreenState())
        private set

    var dialogState by mutableStateOf(FormScreenDialogState())
        private set

    /* ===================================================== */
    /* Public Methods */
    /* ===================================================== */

    fun onEvent(event: FormScreenEvent)
    {
        when (event)
        {
            is FormScreenEvent.OnEnterFirstName ->
            {
                this.state = state.copy(
                        firstName = event.str
                )
            }

            is FormScreenEvent.OnEnterLastName ->
            {
                this.state = state.copy(
                        lastName = event.str
                )
            }

            is FormScreenEvent.OnEnterContactNo ->
            {
                this.state = state.copy(
                        contactNo = event.str
                )
            }

            is FormScreenEvent.OnEnterEmail ->
            {
                this.state = state.copy(
                        email = event.str
                )
            }

            is FormScreenEvent.OnClickSubmitButton ->
            {
                this.performSubmitRsvp()
            }

            is FormScreenEvent.OnToggleDialog ->
            {
                this.dialogState = FormScreenDialogState()
            }
        }
    }

    /* ===================================================== */
    /* Private Methods */
    /* ===================================================== */

    private fun validateFields(): Boolean
    {
        if (state.firstName.isEmpty() || state.lastName.isEmpty() || state.contactNo.isEmpty() || state.email.isEmpty())
        {
            return false
        }

        return true
    }

    private fun resetValues()
    {
        this.state = FormScreenState()
    }

    private fun performSubmitRsvp()
    {
        viewModelScope.launch {

            if (!validateFields())
            {
                // TODO: Show error message
                dialogState = dialogState.copy(
                        isDialogShown = true,
                        dialogTitleText = "Error",
                        dialogDescriptionText = "All fields are mandatory"
                )
                return@launch
            }

            sendRsvpUseCase.invoke(
                    firstName = state.firstName,
                    lastName = state.lastName,
                    contactNo = state.contactNo,
                    email = state.email
            ).onSuccess {

                // TODO: Show success message
                dialogState = dialogState.copy(
                        isDialogShown = true,
                        dialogTitleText = "THANK YOU",
                        dialogDescriptionText = it
                )

            }.onFailure {
                resetValues()
                // TODO: Show error message
                dialogState = dialogState.copy(
                        isDialogShown = true,
                        dialogTitleText = "Error",
                        dialogDescriptionText = it.localizedMessage
                )
            }
        }
    }
}

data class FormScreenState(
        val firstName: String = "",
        val lastName: String = "",
        val contactNo: String = "",
        val email: String = ""
)

data class FormScreenDialogState(
        val isDialogShown: Boolean = false,
        val dialogTitleText: String = "",
        val dialogDescriptionText: String = ""
)