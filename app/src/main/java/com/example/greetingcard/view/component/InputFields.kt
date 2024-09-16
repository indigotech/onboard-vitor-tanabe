package com.example.greetingcard.view.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greetingcard.viewModel.NewUserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputFields(
    value: String,
    title: String,
    type: KeyboardType,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    isDateField: Boolean = false

) {
    Column() {
        val viewModel: NewUserViewModel = viewModel()
        InputLabel(title = title)
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= maxLength) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .border(1.dp, Color(0xFF777777), RoundedCornerShape(16.dp)),
            keyboardOptions = KeyboardOptions(
                keyboardType = type
            ),
            shape = RoundedCornerShape(16.dp),
            visualTransformation = when {
                isPassword -> PasswordVisualTransformation()
                isDateField -> viewModel.dateVisualTransformation()
                else -> VisualTransformation.None
            },
        )
    }
}

@Composable
private fun InputLabel(title: String) {
    Box(
        modifier = Modifier
            .padding(bottom = 12.dp)
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF777777)
        )
    }
}