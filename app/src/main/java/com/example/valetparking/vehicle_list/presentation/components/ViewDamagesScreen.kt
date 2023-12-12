package com.example.valetparking.vehicle_list.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ViewDamagesScreen(bitmap: Bitmap, onBackToList: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            text = "Return to list",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .clickable { onBackToList() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "",
        )
    }
}