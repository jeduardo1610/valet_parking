package com.example.valetparking

import android.app.Application
import com.example.valetparking.di.DaggerValetParkingComponent
import com.example.valetparking.di.ValetParkingActivityComponentProvider
import com.example.valetparking.di.ValetParkingAppModule
import com.example.valetparking.di.ValetParkingComponent

class ValetParkingApplication : Application(), ValetParkingActivityComponentProvider {

    private lateinit var component: ValetParkingComponent

    override fun onCreate() {
        component =
            DaggerValetParkingComponent.builder().valetParkingAppModule(ValetParkingAppModule(this))
                .build()
        super.onCreate()
    }

    override fun getValetParkingComponent(): ValetParkingComponent {
        return component
    }
}
