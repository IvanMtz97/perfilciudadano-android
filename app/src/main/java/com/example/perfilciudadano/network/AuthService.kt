package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.SignInBody
import com.example.perfilciudadano.models.SignInResponse
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthService {
  private val retrofit = RetrofitService.build()

  suspend fun signIn(folium: String): SignInResponse? {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(AuthApi::class.java).signIn(SignInBody(folium))
      val signInResponse: SignInResponse? = response.body()
      signInResponse
    }
  }
}