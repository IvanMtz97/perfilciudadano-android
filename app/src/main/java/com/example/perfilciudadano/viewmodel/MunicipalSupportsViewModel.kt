package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.MunicipalSupportsService
import kotlinx.coroutines.launch

class MunicipalSupportsViewModel : ViewModel() {
  private val mutableMunicipalSupports = MutableLiveData<List<Option>>()
  private val mutableSelectedMunicipalSupports = MutableLiveData<List<Option>>()
  val municipalSupports: LiveData<List<Option>> get() = mutableMunicipalSupports
  val selectedMunicipalSupports: LiveData<List<Option>> get() = mutableSelectedMunicipalSupports

  fun selectMunicipalSupports(options: List<Option>) {
    mutableSelectedMunicipalSupports.value = options
  }

  fun getAllMunicipalSupports() {
    viewModelScope.launch {
      val municipalSupports = MunicipalSupportsService().getAllMunicipalSupports()

      if (!municipalSupports.isNullOrEmpty()) {
        mutableMunicipalSupports.postValue(municipalSupports)
      }
    }
  }
}