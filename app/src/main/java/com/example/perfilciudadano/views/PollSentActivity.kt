package com.example.perfilciudadano.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.perfilciudadano.R

class PollSentActivity : AppCompatActivity() {
  private lateinit var txtFolium: TextView
  private lateinit var txtFoliumLabel: TextView
  private lateinit var btnFinish: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_poll_sent)
    this.supportActionBar?.hide()
    val generatedFolium = intent.getStringExtra("generatedFolium")

    txtFolium = findViewById(R.id.txtFolium)
    txtFoliumLabel = findViewById(R.id.txtFoliumLabel)
    btnFinish = findViewById(R.id.btnFinish)
    if (generatedFolium.isNullOrEmpty()) {
      txtFolium.visibility = View.GONE
      txtFoliumLabel.visibility = View.GONE
    } else {
      txtFolium.setText(generatedFolium)
    }

    btnFinish.setOnClickListener {
      var intent = Intent(this, HomeActivity::class.java)
      finish()
      startActivity(intent)
    }
  }
}