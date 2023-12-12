package com.example.valetparking.vehicle_list.domain.repository

import com.example.valetparking.db.DBVehicle
import com.example.valetparking.db.VehiclesDao
import javax.inject.Inject

class VehiclesListRepositoryImpl @Inject constructor(private val dao: VehiclesDao) :
    VehiclesListRepository {
    override suspend fun getAllVehicles(): List<DBVehicle> {
        return dao.getAll()
    }
}
