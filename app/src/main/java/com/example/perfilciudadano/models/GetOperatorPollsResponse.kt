package com.example.perfilciudadano.models

data class OperatorPoll (
  val id: Int,
  val createdat: String,
  val createdbyfolium: Int,
  val generatedfolium: Int,
  val generatedfoliumid: Int,
  val name: String,
  val structs: Int,
)

data class GetOperatorPollsResponse(
  val success: Boolean,
  val data: List<OperatorPoll>?,
)
