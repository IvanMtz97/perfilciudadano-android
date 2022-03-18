package com.example.perfilciudadano.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
  fun build(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("http://perfilciudadano.com:3000")
      //.baseUrl("http://192.168.1.101:3000")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
}