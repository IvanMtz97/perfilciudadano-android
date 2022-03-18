package com.example.perfilciudadano.network

import android.util.Log
import com.example.perfilciudadano.models.*
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PollService {
  private val retrofit = RetrofitService.build()

  suspend fun sendPoll(poll: Poll): SendPollresponse? {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(PollApi::class.java).sendPoll(poll)
      val sendPollResponse = response.body()
      sendPollResponse
    }
  }

  suspend fun getCurps(curp: String): GetCurpsResponse? {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(PollApi::class.java).getCurps(GetCurpsBody(curp))
      response.body()
    }
  }

  suspend fun getLeaderPolls(foliumId: Int): GetLeaderPollsResponse? {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(PollApi::class.java).getLeaderPolls(foliumId)
      response.body()
    }
  }

  suspend fun getOperatorPolls(foliumId: Int): GetOperatorPollsResponse? {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(PollApi::class.java).getOperatorPolls(foliumId)
      response.body()
    }
  }
}