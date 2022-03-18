package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.*
import retrofit2.Response
import retrofit2.http.*

interface PollApi {
  @POST("/poll")
  suspend fun sendPoll(@Body poll: Poll): Response<SendPollresponse>

  @POST("/curps")
  suspend fun getCurps(@Body body: GetCurpsBody): Response<GetCurpsResponse>

  @GET("/poll/appleader/{id}")
  suspend fun getLeaderPolls(@Path("id") foliumId: Int): Response<GetLeaderPollsResponse>

  @GET("/poll/appoperator/{id}")
  suspend fun getOperatorPolls(@Path("id") foliumId: Int): Response<GetOperatorPollsResponse>
}