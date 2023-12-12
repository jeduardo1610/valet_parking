package com.example.valetparking.vehicle_list.di

import com.example.valetparking.vehicle_list.domain.repository.VehiclesListRepository
import com.example.valetparking.vehicle_list.domain.repository.VehiclesListRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class VehiclesListModule {

    @Provides
    fun provideVehiclesListRepository(impl: VehiclesListRepositoryImpl): VehiclesListRepository {
        return impl
    }

}
