package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.SoccerTeamsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SoccerTeamsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllSoccerTeams(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(SoccerTeamsApi::class.java).getAllSoccerTeams()
      val soccerTeams = response.body() ?: emptyList()
      SoccerTeamsProvider.soccerTeams = soccerTeams
      soccerTeams
    }
  }
}