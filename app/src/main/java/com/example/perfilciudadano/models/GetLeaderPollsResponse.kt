package com.example.perfilciudadano.models

data class LeaderPolls (
  val id: Int,
  val createdat: String,
  val name: String,
)

data class GetLeaderPollsResponse(
  val success: Boolean,
  val data: List<LeaderPolls>?,
)
