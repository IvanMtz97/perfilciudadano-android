package com.example.perfilciudadano.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perfilciudadano.models.Poll

class PollViewModel : ViewModel() {
  private val mutablePoll = MutableLiveData<Poll>()
  private val mutablePollErrors = MutableLiveData<MutableList<String>>(mutableListOf(
    "Curp",
    "Name",
    "SurName",
    "SecondSurName",
    "BirthDate",
    "BirthPlace",
    "IneExpirationYear",
    "ZipCode",
    "Section",
    "Colony",
    "Street",
    "ExteriorNumber",
    "MaritalStatus",
    "FamilyPosition",
    "CellPhoneNumber",
    "FamilyIntegrantsNumber",
  ))
  val poll: LiveData<Poll> get() = mutablePoll
  val pollErrors: LiveData<MutableList<String>> get() = mutablePollErrors

  fun updatePoll(updatedPoll: Poll) {
    mutablePoll.postValue(updatedPoll)
  }

  fun addPollError(fieldId: String) {
    val list = mutablePollErrors.value ?: mutableListOf()
    val errorIndex = list.indexOf(fieldId)

    if (errorIndex == -1) {
      list.add(fieldId)
    }
    mutablePollErrors.value = list ?: mutableListOf()
  }

  fun removePollError(fieldId: String) {
    val list = mutablePollErrors.value
    list?.remove(fieldId)
    mutablePollErrors.value = list ?: mutableListOf()
  }

  fun sendPoll(poll: Poll) {
    Log.v("SEND POLL", poll.toString())
  }
}