package com.example.valetparking.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DBVehicle::class], version = 2, exportSchema = true)
abstract class ValetParkingDatabase : RoomDatabase() {

    abstract val vehiclesDao: VehiclesDao

    companion object {
        const val VALET_PARKING_DATABASE = "valet_parking_database"
    }
}
