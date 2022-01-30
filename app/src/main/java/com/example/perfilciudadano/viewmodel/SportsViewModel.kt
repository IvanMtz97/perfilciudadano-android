package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.SportsService
import kotlinx.coroutines.launch

class SportsViewModel : ViewModel() {
  private val mutableSports = MutableLiveData<List<Option>>()
  private val mutableSelectedSports = MutableLiveData<List<Option>>()
  val sports: LiveData<List<Option>> get() = mutableSports
  val selectedSports: LiveData<List<Option>> get() = mutableSelectedSports

  fun selectSports(options: List<Option>) {
    mutableSelectedSports.value = options
  }

  fun getAllSports() {
    viewModelScope.launch {
      val sports = SportsService().getAllSports()

      if (!sports.isNullOrEmpty()) {
        mutableSports.postValue(sports)
      }
    }
  }
}