package com.example.valetparking.register_vehicle.domain.repository

import com.example.valetparking.db.DBVehicle

interface RegisterVehicleRepository {

    fun getNewId(): String

    suspend fun insertCartEntry(color: String, brand: String, plates: String, uuid: String)

    suspend fun getVehicleEntry(id: String): DBVehicle

}
