package com.example.valetparking.register_vehicle.di

import com.example.valetparking.register_vehicle.presentation.RegisterVehicleFragment
import dagger.Subcomponent

@Subcomponent(modules = [RegisterVehicleModule::class])
interface RegisterVehicleSubComponent {

    fun inject(fragment: RegisterVehicleFragment)

}
