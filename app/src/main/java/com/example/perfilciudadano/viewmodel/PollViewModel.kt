package com.example.perfilciudadano.viewmodel

import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Poll
import com.example.perfilciudadano.models.SendPollresponse
import com.example.perfilciudadano.network.PollService
import com.example.perfilciudadano.views.PollSentActivity
import kotlinx.coroutines.launch

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
    "PhoneNumber",
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

  fun sendPoll(poll: Poll, activity: FragmentActivity?) {
    val pollService = PollService()
    viewModelScope.launch {
      Log.v("SEND POLL", poll.toString())
      val response: SendPollresponse? = pollService.sendPoll(poll)
      Log.v("SEND POLL RESPONSE", response.toString())
      response.let {
        if (response != null && activity != null) {
          if (response.success) {
            val intent = Intent(activity, PollSentActivity::class.java)
            intent.putExtra("generatedFolium", response.folium)
            activity.finish()
            activity.startActivity(intent)
          }
        }
      }
    }
  }
}