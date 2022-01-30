package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.HobbiesService
import kotlinx.coroutines.launch

class HobbiesViewModel : ViewModel() {
  private val mutableHobbies = MutableLiveData<List<Option>>()
  private val mutableSelectedHobbies = MutableLiveData<List<Option>>()
  val hobbies: LiveData<List<Option>> get() = mutableHobbies
  val selectedHobbies: LiveData<List<Option>> get() = mutableSelectedHobbies

  fun selectHobbies(options: List<Option>) {
    mutableSelectedHobbies.value = options
  }

  fun getAllHobbies() {
    viewModelScope.launch {
      val hobbies = HobbiesService().getAllHobbies()

      if (!hobbies.isNullOrEmpty()) {
        mutableHobbies.postValue(hobbies)
      }
    }
  }
}