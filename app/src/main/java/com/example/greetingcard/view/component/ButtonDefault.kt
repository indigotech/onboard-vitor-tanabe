package com.example.greetingcard.view.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ButtonDefault(buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(44.dp),
        colors = ButtonDefaults.buttonColors(Color.Cyan)
    ) {
        ButtonText(buttonText)
    }
}

@Composable
private fun ButtonText(buttonText: String) {
    Text(
        text = buttonText,
        fontWeight = FontWeight.Normal,
        color = Color.Black
    )
}
