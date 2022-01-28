package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.ColoniesService
import kotlinx.coroutines.launch

class ColoniesViewModel : ViewModel() {
  private val mutableColonies = MutableLiveData<List<Option>>()
  private val mutableSelectedColony = MutableLiveData<Option>()
  val selectedColony: LiveData<Option> get() = mutableSelectedColony
  val colonies: LiveData<List<Option>> get() = mutableColonies

  fun selectColony(colonyOption: Option) {
    mutableSelectedColony.value = colonyOption
  }

  fun getColoniesByZipCode(zipCode: String) {
    viewModelScope.launch {
      val colonies = ColoniesService().getColoniesByZipCode(zipCode)
      if (!colonies.isNullOrEmpty()) {
        mutableColonies.postValue(colonies)
      }
    }
  }

  fun clear() {
    // TODO: Clear selected colony and colonies when zipcode change
  }
}