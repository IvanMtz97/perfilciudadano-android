package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.SignInBody
import com.example.perfilciudadano.models.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
  @POST("/signin")
  suspend fun signIn(@Body body: SignInBody): Response<SignInResponse>
}