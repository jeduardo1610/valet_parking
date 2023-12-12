package com.example.valetparking.vehicle_list.domain.repository

import com.example.valetparking.db.DBVehicle

interface VehiclesListRepository {

    suspend fun getAllVehicles(): List<DBVehicle>

}
