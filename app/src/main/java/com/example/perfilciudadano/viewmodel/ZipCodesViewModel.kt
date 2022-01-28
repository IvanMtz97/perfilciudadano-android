package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.ZipCodesService
import kotlinx.coroutines.launch

class ZipCodesViewModel : ViewModel() {
  private val mutableZipCodes = MutableLiveData<List<Option>>()
  private val mutableSelectedZipCode = MutableLiveData<Option>()
  val selectedZipCode: LiveData<Option> get() = mutableSelectedZipCode
  val zipCodes: LiveData<List<Option>> get() = mutableZipCodes

  fun selectZipCode(zipCodeOption: Option) {
    mutableSelectedZipCode.value = zipCodeOption
  }

  fun getAllZipCodes() {
    viewModelScope.launch {
      val zipCodes = ZipCodesService().getAllZipCodes()

      if (!zipCodes.isNullOrEmpty()) {
        mutableZipCodes.postValue(zipCodes)
      }
    }
  }
}