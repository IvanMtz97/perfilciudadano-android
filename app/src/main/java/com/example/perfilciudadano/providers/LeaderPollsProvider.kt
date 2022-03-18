package com.example.perfilciudadano.providers

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.perfilciudadano.models.LeaderPolls
import com.example.perfilciudadano.network.PollService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LeaderPollsProvider {
  companion object {
    private var Polls: List<LeaderPolls> = emptyList()

    fun getPolls(foliumId: Int): List<LeaderPolls> {
      runBlocking {
        launch {
          val response = PollService().getLeaderPolls(foliumId)
          Polls = response?.data ?: emptyList()
        }
      }
      return Polls
    }
  }
}