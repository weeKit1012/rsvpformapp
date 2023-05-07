package com.example.rsvpformapp.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun RATextField(
        modifier: Modifier = Modifier,
        onValueChanged: (String) -> Unit,
        strLabel: String,
        strPlaceHolder: String,
        value: String,
        visualTransformation: VisualTransformation? = null,
        keyboardOptions: KeyboardOptions
)
{
    TextField(
            modifier = modifier,
            label = { Text(text = strLabel, color = Color.DarkGray, style = MaterialTheme.typography.body2) },
            placeholder = {
                Text(text = strPlaceHolder,
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray)
            },
            value = value,
            onValueChange = onValueChanged,
            visualTransformation = visualTransformation ?: VisualTransformation.None,
            textStyle = MaterialTheme.typography.body1,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),

            )
}