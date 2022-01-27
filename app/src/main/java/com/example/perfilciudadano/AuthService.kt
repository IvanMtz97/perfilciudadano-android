package com.example.perfilciudadano

import com.example.perfilciudadano.models.SignInBodyModel
import com.example.perfilciudadano.models.SignInResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
  @POST("/signin")
  @Headers("Content-Type:application/json")
  fun signIn(
    @Body folium: SignInBodyModel
  ): Call<SignInResponseModel>
}