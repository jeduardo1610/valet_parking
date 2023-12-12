package com.example.valetparking.vehicle_list.presentation

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import com.example.valetparking.databinding.VehiclesListFragmentBinding
import com.example.valetparking.vehicle_list.di.VehiclesListSubComponent
import com.example.valetparking.vehicle_list.presentation.components.VehiclesListScreen
import javax.inject.Inject

class VehiclesListFragment : Fragment() {

    private lateinit var binding: VehiclesListFragmentBinding

    private var listener: Listener? = null

    private var component: VehiclesListSubComponent? = null

    @Inject
    lateinit var viewModel: VehiclesListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = listener?.getVehiclesListSubComponent()
        component?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VehiclesListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val directory = ContextWrapper(context).getDir(
            "vehicles",
            Context.MODE_PRIVATE
        )
        binding.composeVehiclesList.setContent {
            VehiclesListScreen(state = viewModel.vehiclesListState.collectAsState().value,
                onRegisterVehicle = {
                    listener?.navigateToRegisterVehicle()
                },
                onViewDamages = {
                    viewModel.viewDamages(it)
                },
                onBackToList = {
                    viewModel.backToList(directory)
                },
                onEditVehicle = {
                    listener?.editVehicleEntry(it)
                })
        }
        viewModel.init(directory)
    }

    interface Listener {
        fun getVehiclesListSubComponent(): VehiclesListSubComponent
        fun navigateToRegisterVehicle()
        fun editVehicleEntry(id: String)
    }

    companion object {
        fun getInstance(): VehiclesListFragment {
            return VehiclesListFragment()
        }
    }
}
