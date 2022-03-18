package com.example.perfilciudadano.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.perfilciudadano.R

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle ?) {
    setTheme(R.style.Theme_Perfilciudadano)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    this.supportActionBar?.hide()
    val intent = Intent(this, SignInActivity::class.java)
    finish()
    startActivity(intent)
  }
}