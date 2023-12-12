package com.example.valetparking.di

import com.example.valetparking.register_vehicle.di.RegisterVehicleSubComponent
import com.example.valetparking.vehicle_list.di.VehiclesListSubComponent
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ValetParkingAppModule::class])
@Singleton
interface ValetParkingComponent {

    fun registerVehicleSubComponent(): RegisterVehicleSubComponent

    fun vehiclesListSubComponent(): VehiclesListSubComponent

}
