package com.example.perfilciudadano.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perfilciudadano.models.Poll

class PollViewModel : ViewModel() {
  private val mutablePoll = MutableLiveData<Poll>()
  val poll: LiveData<Poll> get() = mutablePoll

  fun updatePoll(updatedPoll: Poll) {
    mutablePoll.postValue(updatedPoll)
  }

  fun sendPoll(poll: Poll) {
    Log.v("SEND POLL", poll.toString())
  }
}