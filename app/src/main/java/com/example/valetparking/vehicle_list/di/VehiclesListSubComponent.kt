package com.example.valetparking.vehicle_list.di

import com.example.valetparking.vehicle_list.presentation.VehiclesListFragment
import dagger.Subcomponent

@Subcomponent(modules = [VehiclesListModule::class])
interface VehiclesListSubComponent {

    fun inject(fragment: VehiclesListFragment)

}
