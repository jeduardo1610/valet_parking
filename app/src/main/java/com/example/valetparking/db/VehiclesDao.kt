package com.example.valetparking.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.valetparking.db.ValetParkingDatabase.Companion.VALET_PARKING_DATABASE

@Dao
abstract class VehiclesDao {

    @Query("SELECT * FROM $VALET_PARKING_DATABASE")
    abstract suspend fun getAll(): List<DBVehicle>

    @Query("SELECT * FROM $VALET_PARKING_DATABASE WHERE id = :id")
    abstract suspend fun getVehicle(id: String): DBVehicle

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertVehicle(vehicle: DBVehicle)

    @Query("DELETE FROM $VALET_PARKING_DATABASE WHERE id = :id")
    abstract suspend fun deleteVehicle(id: String)

    @Query("DELETE FROM $VALET_PARKING_DATABASE")
    abstract suspend fun deleteAll()
}
