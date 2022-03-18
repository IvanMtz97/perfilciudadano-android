package com.example.perfilciudadano.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.perfilciudadano.R

class HomeActivity : AppCompatActivity() {
  private lateinit var btnNewPoll: Button
  private lateinit var btnMyPolls: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.supportActionBar?.hide()
    setContentView(R.layout.activity_home)
    btnNewPoll = findViewById(R.id.btnNewPoll)
    btnMyPolls = findViewById(R.id.btnMyPolls)
    btnNewPoll.setOnClickListener {
      val intent = Intent(this, NewPollActivity::class.java)
      startActivity(intent)
    }
    btnMyPolls.setOnClickListener {
      val sharedPreferences = getSharedPreferences("perfilciudadano", Context.MODE_PRIVATE)
      val foliumType = sharedPreferences.getString("foliumType", "")
      if (foliumType == "LEADER") {
        val foliumId = sharedPreferences.getInt("foliumId", 0)
        val name = sharedPreferences.getString("foliumName", "")
        val surname = sharedPreferences.getString("foliumSurname", "")
        val intent = Intent(this, LeaderPollsActivity::class.java)
        intent.putExtra("foliumId", foliumId)
        intent.putExtra("foliumFullName", "$name $surname")
        startActivity(intent)
      } else if (foliumType == "OPERATOR") {
        val intent = Intent(this, OperatorPollsActivity::class.java)
        startActivity(intent)
      }
    }
  }
}