package com.example.valetparking.register_vehicle.presentation.components

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.valetparking.register_vehicle.presentation.RegisterVehicleViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterVehicleScreen(
    state: RegisterVehicleViewModel.ValetParkingState,
    onRegister: (RegisterVehicleViewModel.RegisterModel) -> Unit,
    onDamageRegisterDone: (Bitmap, File) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        if (state.registerDamages) {
            VehicleDamageScreen(onDamageRegisterDone, state.loading)
        } else {
            val plates = remember { mutableStateOf("") }
            val color = remember { mutableStateOf("") }
            val brand = remember { mutableStateOf("") }

            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = plates.value,
                onValueChange = { plates.value = it },
                label = {
                    Text(
                        "Plates"
                    )
                }
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = brand.value,
                onValueChange = { brand.value = it },
                label = {
                    Text(
                        "Brand"
                    )
                }
            )
            Spacer(Modifier.height(16.dp))
            VehicleColorDropdown {
                color.value = it
            }

            Spacer(Modifier.height(16.dp))

            OutlinedButton(
                onClick = {
                    if (plates.value.isBlank() || brand.value.isBlank() || color.value.isBlank()) {
                        Toast.makeText(
                            context,
                            "You must fill up all the required fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        onRegister(
                            RegisterVehicleViewModel.RegisterModel(
                                plates.value,
                                brand.value,
                                color.value
                            )
                        )
                    }
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp),
                contentPadding = PaddingValues(all = 16.dp),
                enabled = true
            ) {
                Text(
                    text = "Register",
                    modifier = Modifier
                        .padding(0.dp)
                        .defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.modelToEdit != null) {
                Text(text = "Editing now", color = Color.Red, fontWeight = FontWeight.Bold)
                Text(text = "Plates: ${state.modelToEdit.plates}", color = Color.Black)
                Text(text = "Brand: ${state.modelToEdit.brand}", color = Color.Black)
                Text(text = "Color: ${state.modelToEdit.color}", color = Color.Black)
            }

            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}

@Composable
@Preview
fun RegisterVehicleScreenPreview() {
    RegisterVehicleScreen(
        state = RegisterVehicleViewModel.ValetParkingState(
            registerDamages = false,
            modelToEdit = RegisterVehicleViewModel.RegisterModel(
                "",
                "",
                ""
            )
        ), onRegister = {}, onDamageRegisterDone = { _, _ -> })
}
