package com.example.valetparking.register_vehicle.di

import com.example.valetparking.register_vehicle.domain.repository.RegisterVehicleRepository
import com.example.valetparking.register_vehicle.domain.repository.RegisterVehicleRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RegisterVehicleModule {

    @Provides
    fun provideValetParkingRepository(impl: RegisterVehicleRepositoryImpl): RegisterVehicleRepository {
        return impl
    }

}
