package com.example.rsvpformapp.presentation.form

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rsvpformapp.presentation.components.RAActionButton
import com.example.rsvpformapp.presentation.components.RADialog
import com.example.rsvpformapp.presentation.components.RATextField

@Composable
fun FormScreen(
        viewModel: FormViewModel = hiltViewModel()
)
{
    /* ===================================================== */
    /* Stored Properties */
    /* ===================================================== */

    val state = viewModel.state
    val dialogState = viewModel.dialogState
    val focusManager = LocalFocusManager.current

    /* ===================================================== */
    /* UI */
    /* ===================================================== */

    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState(), enabled = true)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Red)
            )
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
        ) {

            // Top Section
            Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "RSVP Form", style = MaterialTheme.typography.h5, color = Color.White, fontWeight = FontWeight.Bold)
                Divider(
                        color = Color.White,
                        modifier = Modifier
                            .height(50.dp)
                            .width(1.dp)
                            .padding(0.dp, 10.dp)
                )
                Text(text = "Kindly respond by certain date.\nWe look forward to celebrate with you", style = MaterialTheme.typography.body1, color = Color.White, textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Bottom Section
            Card(modifier = Modifier.fillMaxWidth(),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 30.dp)
                ) {

                    Text(text = "Please enter all the fields", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(30.dp))

                    RATextField(
                            onValueChanged = {
                                viewModel.onEvent(FormScreenEvent.OnEnterFirstName(it))
                            }, strLabel = "First Name",
                            strPlaceHolder = "Enter first name",
                            value = state.firstName,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    RATextField(
                            onValueChanged = {
                                viewModel.onEvent(FormScreenEvent.OnEnterLastName(it))
                            }, strLabel = "Last Name",
                            strPlaceHolder = "Enter last name",
                            value = state.lastName,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    RATextField(
                            onValueChanged = {
                                viewModel.onEvent(FormScreenEvent.OnEnterContactNo(it))
                            }, strLabel = "Contact No",
                            strPlaceHolder = "Enter contact number",
                            value = state.contactNo,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    RATextField(
                            onValueChanged = {
                                viewModel.onEvent(FormScreenEvent.OnEnterEmail(it))
                            }, strLabel = "Email",
                            strPlaceHolder = "Enter email",
                            value = state.email,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            RAActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Submit",
                    onClick = {
                        viewModel.onEvent(FormScreenEvent.OnClickSubmitButton)
                    }
            )
        }

    }

    if (dialogState.isDialogShown)
    {
        RADialog(
                onDismiss = {
                    viewModel.onEvent(FormScreenEvent.OnToggleDialog(false))
                },
                titleText = dialogState.dialogTitleText,
                descriptionText = dialogState.dialogDescriptionText
        )
    }
}





