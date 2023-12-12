package com.example.valetparking.vehicle_list.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class VehicleItemModel(
    val plates: String,
    val color: String,
    val brand: String,
    val id: String,
    val bitmap: Bitmap? = null
)

@Composable
fun VehicleItem(
    model: VehicleItemModel,
    backgroundColor: Color,
    onViewDamages: (String) -> Unit,
    onEditVehicle: (String) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(all = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Text(text = model.plates)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = model.brand)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = model.color)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            color = Color.Red,
            text = "View Damages",
            modifier = Modifier
                .padding(0.dp)
                .defaultMinSize(minHeight = 1.dp, minWidth = 1.dp)
                .clickable {
                    onViewDamages(model.id)
                },
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            color = Color.Red,
            text = "Edit",
            modifier = Modifier
                .padding(0.dp)
                .defaultMinSize(minHeight = 1.dp, minWidth = 1.dp)
                .clickable { onEditVehicle(model.id) },
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun VehicleItemPreview() {
    VehicleItem(
        model = VehicleItemModel(plates = "ASD234", color = "Red", brand = "Volkswagen", id = ""),
        backgroundColor = Color.White,
        onViewDamages = {},
        onEditVehicle = {}
    )
}
