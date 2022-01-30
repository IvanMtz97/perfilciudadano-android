package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.FamilyPositionsService
import kotlinx.coroutines.launch

class FamilyPositionsViewModel : ViewModel() {
  private val mutableFamilyPositions = MutableLiveData<List<Option>>()
  private val mutableSelectedFamilyPosition = MutableLiveData<Option>()
  val familyPositions: LiveData<List<Option>> get() = mutableFamilyPositions
  val selectedFamilyPosition: LiveData<Option> get() = mutableSelectedFamilyPosition

  fun selectFamilyPosition(option: Option) {
    mutableSelectedFamilyPosition.value = option
  }

  fun getAllFamilyPositions() {
    viewModelScope.launch {
      val familyPositions = FamilyPositionsService().getAllFamilyPositions()

      if (!familyPositions.isNullOrEmpty()) {
        mutableFamilyPositions.postValue(familyPositions)
      }
    }
  }
}