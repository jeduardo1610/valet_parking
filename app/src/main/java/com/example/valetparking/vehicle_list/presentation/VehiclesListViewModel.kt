package com.example.valetparking.vehicle_list.presentation

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valetparking.vehicle_list.domain.use_cases.GetAllVehiclesUseCase
import com.example.valetparking.vehicle_list.presentation.components.VehicleItemModel
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VehiclesListViewModel @Inject constructor(private val getAllVehiclesUseCase: GetAllVehiclesUseCase) :
    ViewModel() {

    private val _vehiclesListState: MutableStateFlow<VehiclesListState> =
        MutableStateFlow(VehiclesListState())

    val vehiclesListState = _vehiclesListState.asStateFlow()

    fun init(directory: File) {
        viewModelScope.launch(Dispatchers.IO) {
            val vehicles = getAllVehiclesUseCase(directory)
            _vehiclesListState.update { state -> state.copy(vehicles = vehicles) }
        }
    }

    fun backToList(directory: File) {
        viewModelScope.launch(Dispatchers.IO) {
            val vehicles = getAllVehiclesUseCase(directory)
            _vehiclesListState.update { state ->
                state.copy(
                    vehicles = vehicles,
                    viewDamages = false,
                    damagesBitmap = null
                )
            }
        }
    }

    fun viewDamages(id: String) {
        _vehiclesListState.update { state ->
            state.copy(
                viewDamages = true,
                damagesBitmap = getAllVehiclesUseCase.getBitmapForItem(id)
            )
        }
    }

    data class VehiclesListState(
        val vehicles: List<VehicleItemModel> = emptyList(),
        val viewDamages: Boolean = false,
        val damagesBitmap: Bitmap? = null
    )
}
