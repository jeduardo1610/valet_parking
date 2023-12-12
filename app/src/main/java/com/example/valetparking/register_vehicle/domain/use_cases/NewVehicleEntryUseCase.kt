package com.example.valetparking.register_vehicle.domain.use_cases

import com.example.valetparking.register_vehicle.domain.repository.RegisterVehicleRepository
import com.example.valetparking.register_vehicle.presentation.RegisterVehicleViewModel
import javax.inject.Inject

class NewVehicleEntryUseCase @Inject constructor(
    private val repository: RegisterVehicleRepository,
) {
    suspend operator fun invoke(
        registerModel: RegisterVehicleViewModel.RegisterModel,
        uuid: String
    ) {
        with(registerModel) {
            repository.insertCartEntry(this.color, this.brand, this.plates, uuid)
        }
    }

}
