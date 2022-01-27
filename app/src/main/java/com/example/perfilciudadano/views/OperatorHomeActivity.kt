package com.example.perfilciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.perfilciudadano.R

class OperatorHomeActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_operator_home)
    this.supportActionBar?.hide()
  }
}