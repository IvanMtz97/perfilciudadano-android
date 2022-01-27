package com.example.perfilciudadano.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.perfilciudadano.*
import com.example.perfilciudadano.models.SignInBodyModel
import com.example.perfilciudadano.models.SignInResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
  private lateinit var codeFirstDigitInput: EditText
  private lateinit var codeSecondDigitInput: EditText
  private lateinit var codeThirdDigitInput: EditText
  private lateinit var codeFourthDigitInput: EditText
  private lateinit var codeFifthDigitInput: EditText
  private lateinit var signInButton: Button

  fun getFolium(): String {
    return "${codeFirstDigitInput.text}${codeSecondDigitInput.text}${codeThirdDigitInput.text}${codeFourthDigitInput.text}${codeFifthDigitInput.text}"
  }

  fun isFoliumValid(): Boolean {
    return codeFirstDigitInput.text.length > 0 && codeSecondDigitInput.text.length > 0 && codeThirdDigitInput.text.length > 0 && codeFourthDigitInput.text.length > 0 && codeFifthDigitInput.text.length > 0
  }

  fun onSignInInputChange(inputToValidate: EditText, inputToFocus: EditText) {
    if (isFoliumValid()) {
      signInButton.isEnabled = true
    } else {
      signInButton.isEnabled = false
      if (inputToValidate.text.length > 0) inputToFocus.requestFocus()
    }
  }

  fun handleSignInOnClick() {
    val Folium = getFolium()
    val apiGenerator = ApiGenerator.buildService(AuthService::class.java)
    val call = apiGenerator.signIn(SignInBodyModel(Folium))
    call.enqueue(object: Callback<SignInResponseModel> {
      override fun onResponse(
        call: Call<SignInResponseModel>,
        response: Response<SignInResponseModel>
      ) {
        if (response.isSuccessful) {
          if (response.body()?.success == true) {
            if (response.body()?.role == "OPERATOR") {
              val operatorIntent = Intent(applicationContext, OperatorHomeActivity::class.java)
              finish()
              startActivity(operatorIntent)
            } else if (response.body()?.role == "LEADER") {
              val leaderIntent = Intent(applicationContext, LeaderHomeActivity::class.java)
              leaderIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
              finish()
              startActivity(leaderIntent)
            }
          } else {
            Toast.makeText(applicationContext, "El folio no es correcto", Toast.LENGTH_LONG).show()
          }
        }
      }

      override fun onFailure(call: Call<SignInResponseModel>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_in)
    this.supportActionBar?.hide()
    codeFirstDigitInput = findViewById<EditText>(R.id.codeFirstDigitInput)
    codeSecondDigitInput = findViewById<EditText>(R.id.codeSecondDigitInput)
    codeThirdDigitInput = findViewById<EditText>(R.id.codeThirdDigitInput)
    codeFourthDigitInput = findViewById<EditText>(R.id.codeFourthDigitInput)
    codeFifthDigitInput = findViewById<EditText>(R.id.codeFifthDigitInput)
    signInButton = findViewById(R.id.signInButton)
    signInButton.isEnabled = false

    codeFirstDigitInput.doOnTextChanged { text, start, before, count ->
      onSignInInputChange(codeFirstDigitInput, codeSecondDigitInput)
    }
    codeSecondDigitInput.doOnTextChanged { text, start, before, count ->
      onSignInInputChange(codeSecondDigitInput, codeThirdDigitInput)
    }
    codeThirdDigitInput.doOnTextChanged { text, start, before, count ->
      onSignInInputChange(codeThirdDigitInput, codeFourthDigitInput)
    }
    codeFourthDigitInput.doOnTextChanged { text, start, before, count ->
      onSignInInputChange(codeFourthDigitInput, codeFifthDigitInput)
    }
    codeFifthDigitInput.doOnTextChanged { text, start, before, count ->
      signInButton.isEnabled = isFoliumValid()
    }
    signInButton.setOnClickListener {
      handleSignInOnClick()
    }
  }
}