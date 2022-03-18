package com.example.perfilciudadano.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.perfilciudadano.R

class UserInformationFragment : Fragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_user_information, container, false)
    val sharedPreferences = requireContext().getSharedPreferences("perfilciudadano", Context.MODE_PRIVATE)
    val name = sharedPreferences.getString("foliumName", "")
    val surname = sharedPreferences.getString("foliumSurname", "")
    val txtUserName = view.findViewById<TextView>(R.id.txtOperatorName)
    txtUserName.setText(name + " " + surname)
    return view
  }
}