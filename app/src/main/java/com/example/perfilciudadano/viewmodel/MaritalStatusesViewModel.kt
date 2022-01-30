package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.MaritalStatusesService
import kotlinx.coroutines.launch

class MaritalStatusesViewModel : ViewModel() {
  private val mutableMaritalStatuses = MutableLiveData<List<Option>>()
  private val mutableSelectedMaritalStatus = MutableLiveData<Option>()
  val selectedMaritalStatus: LiveData<Option> get() = mutableSelectedMaritalStatus
  val maritalStatuses: LiveData<List<Option>> get() = mutableMaritalStatuses

  fun selectMaritalStatus(maritalStatusOption: Option) {
    mutableSelectedMaritalStatus.value = maritalStatusOption
  }

  fun getAllMaritalStatuses() {
    viewModelScope.launch {
      val maritalStatuses = MaritalStatusesService().getAllMaritalStatuses()

      if (!maritalStatuses.isNullOrEmpty()) {
        mutableMaritalStatuses.postValue(maritalStatuses)
      }
    }
  }
}