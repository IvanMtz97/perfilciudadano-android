package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.GovernmentInvitationActivityOrThemesService
import kotlinx.coroutines.launch

class GovernmentInvitationActivityOrThemesViewModel : ViewModel() {
  private val mutableOptions = MutableLiveData<List<Option>>()
  private val mutableSelectedOptions = MutableLiveData<List<Option>>()
  val options: LiveData<List<Option>> get() = mutableOptions
  val selectedOptions: LiveData<List<Option>> get() = mutableSelectedOptions

  fun selectOptions(options: List<Option>) {
    mutableSelectedOptions.value = options
  }

  fun getAllOptions() {
    viewModelScope.launch {
      val options = GovernmentInvitationActivityOrThemesService().getAllGovernmentInvitationActivityOrThemes()

      if (!options.isNullOrEmpty()) {
        mutableOptions.postValue(options)
      }
    }
  }
}