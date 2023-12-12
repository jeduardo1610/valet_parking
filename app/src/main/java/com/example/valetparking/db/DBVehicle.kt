package com.example.valetparking.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.valetparking.db.ValetParkingDatabase.Companion.VALET_PARKING_DATABASE

@Entity(tableName = VALET_PARKING_DATABASE)
data class DBVehicle(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val plates: String,
    val color: String,
    val brand: String
)