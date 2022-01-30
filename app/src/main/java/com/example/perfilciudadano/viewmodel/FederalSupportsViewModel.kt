package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.FederalSupportsService
import kotlinx.coroutines.launch

class FederalSupportsViewModel : ViewModel() {
  private val mutableFederalSupports = MutableLiveData<List<Option>>()
  private val mutableSelectedFederalSupports = MutableLiveData<List<Option>>()
  val federalSupports: LiveData<List<Option>> get() = mutableFederalSupports
  val selectedFederalSupports: LiveData<List<Option>> get() = mutableSelectedFederalSupports

  fun selectFederalSupports(options: List<Option>) {
    mutableSelectedFederalSupports.value = options
  }

  fun getAllFederalSupports() {
    viewModelScope.launch {
      val federalSupports = FederalSupportsService().getAllFederalSupports()

      if (!federalSupports.isNullOrEmpty()) {
        mutableFederalSupports.postValue(federalSupports)
      }
    }
  }

}