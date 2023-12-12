package com.example.valetparking.register_vehicle.domain.use_cases

import com.example.valetparking.register_vehicle.domain.repository.RegisterVehicleRepository
import com.example.valetparking.register_vehicle.presentation.RegisterVehicleViewModel
import javax.inject.Inject

class GetVehicleEntryUseCase @Inject constructor(private val repository: RegisterVehicleRepository) {

    suspend operator fun invoke(id: String): RegisterVehicleViewModel.RegisterModel {
        return with(repository.getVehicleEntry(id)) {
            RegisterVehicleViewModel.RegisterModel(this.plates, this.brand, this.color)
        }
    }

}
