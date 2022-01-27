package com.example.perfilciudadano.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.SectionsService
import kotlinx.coroutines.launch
import kotlin.math.log

class SectionsViewModel : ViewModel() {
  private val mutableSections = MutableLiveData<List<Option>>()
  private val mutableSelectedSection = MutableLiveData<Option>()
  val selectedSection: LiveData<Option> get() = mutableSelectedSection
  val sections: LiveData<List<Option>> get() = mutableSections

  fun selectSection(section: Option) {
    mutableSelectedSection.value = section
  }

  fun getAllSections() {
    viewModelScope.launch {
      var sections = SectionsService().getAllSections()

      if (!sections.isNullOrEmpty()) {
        mutableSections.postValue(sections)
      }
    }
  }
}