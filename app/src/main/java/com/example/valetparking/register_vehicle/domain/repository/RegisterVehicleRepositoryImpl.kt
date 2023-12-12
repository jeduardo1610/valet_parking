package com.example.valetparking.register_vehicle.domain.repository

import com.example.valetparking.db.DBVehicle
import com.example.valetparking.db.VehiclesDao
import java.util.UUID
import javax.inject.Inject

class RegisterVehicleRepositoryImpl @Inject constructor(private val dao: VehiclesDao) :
    RegisterVehicleRepository {
    override fun getNewId(): String {
        return UUID.randomUUID().toString()
    }

    override suspend fun insertCartEntry(
        color: String,
        brand: String,
        plates: String,
        uuid: String
    ) {
        dao.insertVehicle(DBVehicle(uuid, plates, color, brand))
    }

    override suspend fun getVehicleEntry(id: String): DBVehicle {
        return dao.getVehicle(id)
    }
}
