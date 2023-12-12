package com.example.valetparking.register_vehicle.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.valetparking.databinding.RegisterVehicleFragmentBinding
import com.example.valetparking.register_vehicle.di.RegisterVehicleSubComponent
import com.example.valetparking.register_vehicle.presentation.components.RegisterVehicleScreen
import javax.inject.Inject

class RegisterVehicleFragment : Fragment() {

    @Inject
    lateinit var viewModel: RegisterVehicleViewModel

    private var component: RegisterVehicleSubComponent? = null

    private var listener: Listener? = null

    private lateinit var binding: RegisterVehicleFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = listener?.getRegisterVehicleSubComponent()
        component?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterVehicleFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleScreenStates()
        viewModel.registerVehicleEvent.observe(viewLifecycleOwner) {
            handleScreenEvents(it)
        }
        viewModel.init(arguments?.getString(ID_TO_EDIT_PARAM, "") ?: "")
    }

    override fun onDestroyView() {
        viewModel.disposeEvent()
        viewModel.registerVehicleEvent.removeObservers(this)
        super.onDestroyView()
    }


    private fun handleScreenStates() {
        binding.composeRegisterVehicle.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                RegisterVehicleScreen(
                    state = viewModel.valetParkingState.collectAsState().value,
                    onRegister = {
                        viewModel.registerVehicle(it)
                    },
                    onDamageRegisterDone = { bitmap, directory ->
                        viewModel.continueWithVehicleRegistration(bitmap, directory)
                    })
            }

        }
    }

    private fun handleScreenEvents(event: RegisterVehicleViewModel.ValetParkingEvents?) {
        when (event) {
            RegisterVehicleViewModel.ValetParkingEvents.NavigateToList -> {
                listener?.navigateToList()
            }

            else -> {}
        }
    }

    interface Listener {
        fun getRegisterVehicleSubComponent(): RegisterVehicleSubComponent
        fun navigateToList()
    }

    companion object {
        private const val ID_TO_EDIT_PARAM = "ID_TO_EDIT_PARAM"
        fun getInstance(idToEdit: String = ""): RegisterVehicleFragment {
            return RegisterVehicleFragment().apply {
                arguments = Bundle().apply { putString(ID_TO_EDIT_PARAM, idToEdit) }
            }
        }
    }
}
