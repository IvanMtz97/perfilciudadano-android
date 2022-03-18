package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.OptionsService
import kotlinx.coroutines.launch

class OptionsViewModel : ViewModel() {
  private val mutableOptions = MutableLiveData<MutableList<Option>>()
  private val mutableColonies = MutableLiveData<List<Option>>()
  private val mutableSections = MutableLiveData<List<Option>>()
  val options: LiveData<MutableList<Option>> get() = mutableOptions
  val colonies: LiveData<List<Option>> get() = mutableColonies
  val sections: LiveData<List<Option>> get() = mutableSections

  fun getAllOptions() {
    viewModelScope.launch {
      val options = OptionsService().getAllOptions()
      mutableOptions.postValue(options.toMutableList())
    }
  }

  fun getColoniesByZipCode(zipCode: String) {
    viewModelScope.launch {
      val colonies = OptionsService().getColoniesByZipCode(zipCode)
      mutableColonies.postValue(colonies)
    }
  }

  fun getSectionsByColony(colony: String) {
    viewModelScope.launch {
      val sections = OptionsService().getSectionsByColony(colony)
      mutableSections.postValue(sections)
    }
  }

  fun clearColonies() {
    mutableColonies.postValue(emptyList())
  }
}