package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.DiseasesService
import kotlinx.coroutines.launch

class DiseasesViewModel : ViewModel() {
  private val mutableDiseases = MutableLiveData<List<Option>>()
  private val mutableSelectedDiseases = MutableLiveData<List<Option>>()
  val diseases: LiveData<List<Option>> get() = mutableDiseases
  val selectedDiseases: LiveData<List<Option>> get() = mutableSelectedDiseases

  fun selectDiseases(options: List<Option>) {
    mutableSelectedDiseases.value = options
  }

  fun getAllDiseases() {
    viewModelScope.launch {
      val diseases = DiseasesService().getAllDiseases()

      if (!diseases.isNullOrEmpty()) {
        mutableDiseases.postValue(diseases)
      }
    }
  }
}