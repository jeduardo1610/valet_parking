package com.example.valetparking.vehicle_list.domain.use_cases

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.valetparking.vehicle_list.domain.repository.VehiclesListRepository
import com.example.valetparking.vehicle_list.presentation.components.VehicleItemModel
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import javax.inject.Inject

class GetAllVehiclesUseCase @Inject constructor(private val repository: VehiclesListRepository) {

    private var vehiclesList = emptyList<VehicleItemModel>()

    suspend operator fun invoke(directory: File): List<VehicleItemModel> {
        vehiclesList = repository.getAllVehicles()
            .map {
                VehicleItemModel(
                    plates = it.plates,
                    color = it.color,
                    brand = it.brand,
                    id = it.id,
                    bitmap = getBitmap(it.id, directory)
                )
            }
        return vehiclesList
    }

    fun getBitmapForItem(id: String): Bitmap? {
        return vehiclesList.firstOrNull { it.id == id }?.bitmap
    }

    private fun getBitmap(id: String, directory: File): Bitmap? {
        return try {
            BitmapFactory.decodeStream(FileInputStream(File(directory, "${id}.jpg")))
        } catch (e: FileNotFoundException) {
            null
        }
    }
}
