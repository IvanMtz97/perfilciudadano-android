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
  val options: LiveData<MutableList<Option>> get() = mutableOptions
  val colonies: LiveData<List<Option>> get() = mutableColonies

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

  fun clearColonies() {
    mutableColonies.postValue(emptyList())
  }
}