package com.example.perfilciudadano.providers

import android.content.Context
import com.example.perfilciudadano.models.OperatorPoll
import com.example.perfilciudadano.network.PollService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class OperatorPollsProvider {
  companion object {
    private var Polls: List<OperatorPoll> = emptyList()

    fun getPolls(context: Context): List<OperatorPoll> {
      val sharedPreferences = context.getSharedPreferences("perfilciudadano", Context.MODE_PRIVATE)
      val foliumId = sharedPreferences.getInt("foliumId", 0)
      runBlocking {
        launch {
          val response = PollService().getOperatorPolls(foliumId)
          Polls = response?.data ?: emptyList()
        }
      }
      return Polls
    }
  }
}