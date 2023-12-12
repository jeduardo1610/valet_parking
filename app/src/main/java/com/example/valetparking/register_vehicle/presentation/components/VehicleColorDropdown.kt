package com.example.valetparking.register_vehicle.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun VehicleColorDropdown(onColorChange: (String) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val items =
        listOf("Pick a color from the list", "Green", "Red", "White", "Black", "Blue", "Orange")
    val selectedIndex = remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            items[selectedIndex.value], modifier = Modifier
                .width(IntrinsicSize.Max)
                .clickable(onClick = {
                    expanded.value = true
                })
                .background(
                    Color.LightGray
                )
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .background(
                    Color.Gray
                )
        ) {
            items.forEachIndexed { index, s ->
                if (index > 0) {
                    DropdownMenuItem(onClick = {
                        selectedIndex.value = index
                        expanded.value = false
                        onColorChange(s)
                    }, text = { Text(text = s, color = Color.White) })
                }
            }
        }
    }
}
