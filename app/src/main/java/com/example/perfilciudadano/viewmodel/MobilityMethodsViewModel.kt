package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.MobilityMethodsService
import kotlinx.coroutines.launch

class MobilityMethodsViewModel : ViewModel() {
  private val mutableMobilityMethods = MutableLiveData<List<Option>>()
  private val mutableSelectedMobilityMethod = MutableLiveData<Option>()
  val mobilityMethods: LiveData<List<Option>> get() = mutableMobilityMethods
  val selectedMobilityMethod: LiveData<Option> get() = mutableSelectedMobilityMethod

  fun selectMobilityMethod(option: Option) {
    mutableSelectedMobilityMethod.value = option
  }

  fun getAllMobilityMethods() {
    viewModelScope.launch {
      val mobilityMethods = MobilityMethodsService().getAllMobilityMethods()

      if (!mobilityMethods.isNullOrEmpty()) {
        mutableMobilityMethods.postValue(mobilityMethods)
      }
    }
  }

}