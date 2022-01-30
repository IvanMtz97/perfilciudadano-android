package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.ReligionsService
import kotlinx.coroutines.launch

class ReligionsViewModel : ViewModel() {
  private val mutableReligions = MutableLiveData<List<Option>>()
  private val mutableSelectedReligion = MutableLiveData<Option>()
  val religions: LiveData<List<Option>> get() = mutableReligions
  val selectedReligion: LiveData<Option> get() = mutableSelectedReligion

  fun selectReligion(option: Option) {
    mutableSelectedReligion.value = option
  }

  fun getAllReligions() {
    viewModelScope.launch {
      val religions = ReligionsService().getAllReligions()

      if (!religions.isNullOrEmpty()) {
        mutableReligions.postValue(religions)
      }
    }
  }
}