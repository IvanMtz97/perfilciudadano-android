package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.OccupationsService
import kotlinx.coroutines.launch

class OccupationsViewModel : ViewModel() {
  private val mutableOccupations = MutableLiveData<List<Option>>()
  private val mutableSelectedOccupation = MutableLiveData<Option>()
  val occupations: LiveData<List<Option>> get() = mutableOccupations
  val selectedOccupation: LiveData<Option> get() = mutableSelectedOccupation

  fun selectOccupation(option: Option) {
    mutableSelectedOccupation.value = option
  }

  fun getAllOccupations() {
    viewModelScope.launch {
      var occupations = OccupationsService().getAllOccupations()

      if (!occupations.isNullOrEmpty()) {
        mutableOccupations.postValue(occupations)
      }
    }
  }
}