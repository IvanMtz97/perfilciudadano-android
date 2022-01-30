package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.HobbiesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HobbiesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllHobbies(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(HobbiesApi::class.java).getAllHobbies()
      val hobbies = response.body() ?: emptyList()
      HobbiesProvider.hobbies = hobbies
      hobbies
    }
  }
}