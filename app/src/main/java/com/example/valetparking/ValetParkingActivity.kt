package com.example.valetparking

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.valetparking.databinding.MainActivityLayoutBinding
import com.example.valetparking.di.ValetParkingActivityComponentProvider
import com.example.valetparking.register_vehicle.di.RegisterVehicleSubComponent
import com.example.valetparking.register_vehicle.presentation.RegisterVehicleFragment
import com.example.valetparking.vehicle_list.presentation.VehiclesListFragment
import com.example.valetparking.vehicle_list.di.VehiclesListSubComponent

class ValetParkingActivity : FragmentActivity(), RegisterVehicleFragment.Listener,
    VehiclesListFragment.Listener {

    private lateinit var binding: MainActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToFragment(VehiclesListFragment.getInstance())
    }

    override fun getRegisterVehicleSubComponent(): RegisterVehicleSubComponent {
        return (application as ValetParkingActivityComponentProvider).getValetParkingComponent()
            .registerVehicleSubComponent()
    }

    override fun getVehiclesListSubComponent(): VehiclesListSubComponent {
        return (application as ValetParkingActivityComponentProvider).getValetParkingComponent()
            .vehiclesListSubComponent()
    }

    override fun navigateToRegisterVehicle() {
        navigateToFragment(RegisterVehicleFragment.getInstance())
    }

    override fun navigateToList() {
        navigateToFragment(VehiclesListFragment.getInstance())
    }

    override fun editVehicleEntry(id: String) {
        navigateToFragment(RegisterVehicleFragment.getInstance(id))
    }

    private fun navigateToFragment(fragment: Fragment) {
        with(supportFragmentManager) {
            beginTransaction().replace(R.id.main_content, fragment).commit()
        }
    }
}
