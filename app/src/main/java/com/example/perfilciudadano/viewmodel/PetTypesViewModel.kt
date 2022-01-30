package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.PetTypesService
import kotlinx.coroutines.launch

class PetTypesViewModel : ViewModel() {
  private val mutablePetTypes = MutableLiveData<List<Option>>()
  private val mutableSelectedPetTypes = MutableLiveData<List<Option>>()
  val petTypes: LiveData<List<Option>> get() = mutablePetTypes
  val selectedPetTypes: LiveData<List<Option>> get() = mutableSelectedPetTypes

  fun selectPetTypes(options: List<Option>) {
    mutableSelectedPetTypes.value = options
  }

  fun getAllPetTypes() {
    viewModelScope.launch {
      val petTypes = PetTypesService().getAllPetTypes()

      if (!petTypes.isNullOrEmpty()) {
        mutablePetTypes.postValue(petTypes)
      }
    }
  }
}