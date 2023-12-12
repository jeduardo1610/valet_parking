package com.example.valetparking.vehicle_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.valetparking.vehicle_list.presentation.VehiclesListViewModel

@Composable
fun VehiclesListScreen(
    state: VehiclesListViewModel.VehiclesListState,
    onRegisterVehicle: () -> Unit,
    onViewDamages: (String) -> Unit,
    onBackToList: () -> Unit,
    onEditVehicle: (String) -> Unit
) {

    val listState = rememberLazyListState()

    Box {
        if (state.viewDamages) {
            state.damagesBitmap?.let {
                ViewDamagesScreen(bitmap = it, onBackToList = onBackToList)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.White),
                state = listState
            ) {

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        text = "Register new vehicle",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                            .clickable { onRegisterVehicle() }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                itemsIndexed(items = state.vehicles) { index: Int, item: VehicleItemModel ->
                    VehicleItem(
                        model = item,
                        backgroundColor = if (index % 2 == 0) Color.LightGray else Color.White,
                        onViewDamages = onViewDamages,
                        onEditVehicle = onEditVehicle
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun VehiclesListScreenPreview() {
    VehiclesListScreen(
        VehiclesListViewModel.VehiclesListState(
            vehicles = listOf(
                VehicleItemModel(
                    plates = "ASD234",
                    color = "Red",
                    brand = "Volkswagen",
                    id = ""
                ),
                VehicleItemModel(
                    plates = "DFHG3456",
                    color = "Orange",
                    brand = "Ford",
                    id = ""
                )
            ), viewDamages = false
        ),
        {},
        {},
        {},
        {}
    )
}
