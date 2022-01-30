package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.StudyDegreesService
import kotlinx.coroutines.launch

class StudyDegreesViewModel : ViewModel() {
  private val mutableStudyDegrees = MutableLiveData<List<Option>>()
  private val mutableSelectedStudyDegree = MutableLiveData<Option>()
  val studyDegrees: LiveData<List<Option>> get() = mutableStudyDegrees
  val selectedStudyDegree: LiveData<Option> get() = mutableSelectedStudyDegree

  fun selectStudyDegree(option: Option) {
    mutableSelectedStudyDegree.value = option
  }

  fun getAllStudyDegrees() {
    viewModelScope.launch {
      val studyDegrees = StudyDegreesService().getAllStudyDegrees()

      if (!studyDegrees.isNullOrEmpty()) {
        mutableStudyDegrees.postValue(studyDegrees)
      }
    }
  }
}