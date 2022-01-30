package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.StateSupportsService
import kotlinx.coroutines.launch

class StateSupportsViewModel : ViewModel() {
  private val mutableStateSupports = MutableLiveData<List<Option>>()
  private val mutableSelectedStateSupports = MutableLiveData<List<Option>>()
  val stateSupports: LiveData<List<Option>> get() = mutableStateSupports
  val selectedStateSupports: LiveData<List<Option>> get() = mutableSelectedStateSupports

  fun selectStateSupports(options: List<Option>) {
    mutableSelectedStateSupports.value = options
  }

  fun getAllStateSupports() {
    viewModelScope.launch {
      val stateSupports = StateSupportsService().getAllStateSupports()

      if (!stateSupports.isNullOrEmpty()) {
        mutableStateSupports.postValue(stateSupports)
      }
    }
  }
}