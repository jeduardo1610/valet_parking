package com.example.valetparking.di

import android.content.Context
import androidx.room.Room
import com.example.valetparking.db.ValetParkingDatabase
import com.example.valetparking.db.VehiclesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ValetParkingAppModule constructor(val context: Context) {

    @Singleton
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideDatabase(): ValetParkingDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ValetParkingDatabase::class.java,
            ValetParkingDatabase.VALET_PARKING_DATABASE
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideVehiclesDao(database: ValetParkingDatabase): VehiclesDao {
        return database.vehiclesDao
    }
}
