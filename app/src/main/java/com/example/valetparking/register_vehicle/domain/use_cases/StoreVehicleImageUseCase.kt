package com.example.valetparking.register_vehicle.domain.use_cases

import android.graphics.Bitmap
import com.example.valetparking.register_vehicle.domain.repository.RegisterVehicleRepository
import java.io.File
import javax.inject.Inject

class StoreVehicleImageUseCase @Inject constructor(private val repository: RegisterVehicleRepository) {
    suspend operator fun invoke(
        bitmap: Bitmap,
        directory: File,
        existingUuid: String = ""
    ): String {
        val uuid = existingUuid.ifBlank { repository.getNewId() }
        with(File(directory, "${uuid}.jpg")) {
            this.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 85, out)
                out.flush()
                out.close()
            }
        }
        return uuid
    }
}
