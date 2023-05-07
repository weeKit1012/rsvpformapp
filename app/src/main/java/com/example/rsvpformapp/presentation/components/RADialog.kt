package com.example.rsvpformapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun RADialog(
        onDismiss: () -> Unit,
        titleText: String,
        descriptionText: String
)
{
    Dialog(onDismissRequest = onDismiss
    ) {
        Surface(
                shape = RoundedCornerShape(15.dp),
                color = Color.White
        ) {
            Box(contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = titleText, style = MaterialTheme.typography.h5, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = descriptionText, style = MaterialTheme.typography.body1, color = Color.DarkGray, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(30.dp))
                    RAActionButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "OK",
                            onClick = onDismiss
                    )
                }
            }
        }
    }
}