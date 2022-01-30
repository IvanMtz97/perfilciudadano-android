package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.PollutionCausesService
import kotlinx.coroutines.launch

class PollutionCausesViewModel : ViewModel() {
  val mutableOptions = MutableLiveData<List<Option>>()
  val mutableSelectedOption = MutableLiveData<Option>()
  val options: LiveData<List<Option>> get() = mutableOptions
  val selectedOption: LiveData<Option> get() = mutableSelectedOption

  fun selectOption(option: Option) {
    mutableSelectedOption.value = option
  }

  fun getAllOptions() {
    viewModelScope.launch {
      mutableOptions.postValue(PollutionCausesService().getAllPolutionCauses())
    }
  }
}