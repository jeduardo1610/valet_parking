package com.example.valetparking.register_vehicle.presentation

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valetparking.register_vehicle.domain.use_cases.GetVehicleEntryUseCase
import com.example.valetparking.register_vehicle.domain.use_cases.NewVehicleEntryUseCase
import com.example.valetparking.register_vehicle.domain.use_cases.StoreVehicleImageUseCase
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterVehicleViewModel @Inject constructor(
    private val newVehicleEntryUseCase: NewVehicleEntryUseCase,
    private val storeVehicleImageUseCase: StoreVehicleImageUseCase,
    private val getVehicleEntryUseCase: GetVehicleEntryUseCase
) :
    ViewModel() {

    private val _valetParkingState: MutableStateFlow<ValetParkingState> =
        MutableStateFlow(ValetParkingState())

    val valetParkingState = _valetParkingState.asStateFlow()

    val registerVehicleEvent: LiveData<ValetParkingEvents?>
        get() = registerVehicleEventMutableLiveData

    private val registerVehicleEventMutableLiveData: MutableLiveData<ValetParkingEvents?> =
        MutableLiveData()

    private var uuid: String = ""
    private var uuidToEdit: String = ""

    fun init(idToEdit: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (idToEdit.isNullOrBlank()) {
                _valetParkingState.update { state ->
                    state.copy()
                }
            } else {
                uuidToEdit = idToEdit
                val vehicleEntry = getVehicleEntryUseCase(idToEdit)
                _valetParkingState.update { state -> state.copy(modelToEdit = vehicleEntry) }
            }
        }
    }

    fun continueWithVehicleRegistration(
        bitmap: Bitmap,
        directory: File,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _valetParkingState.update { state ->
                state.copy(loading = true)
            }
            uuid = storeVehicleImageUseCase(bitmap, directory, uuidToEdit)
            _valetParkingState.update { state ->
                state.copy(loading = false, registerDamages = false)
            }
        }
    }

    fun registerVehicle(model: RegisterModel) {
        viewModelScope.launch(Dispatchers.IO) {
            newVehicleEntryUseCase(model, uuid)
            registerVehicleEventMutableLiveData.postValue(ValetParkingEvents.NavigateToList)
        }
    }

    fun disposeEvent() {
        registerVehicleEventMutableLiveData.value = null
    }

    data class ValetParkingState(
        val modelToEdit: RegisterModel? = null,
        val registerDamages: Boolean = true,
        val loading: Boolean = false
    )

    sealed class ValetParkingEvents {
        object NavigateToList : ValetParkingEvents()
    }

    data class RegisterModel(val plates: String, val brand: String, val color: String)

}
