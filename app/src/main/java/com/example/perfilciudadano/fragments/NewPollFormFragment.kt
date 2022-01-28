package com.example.perfilciudadano.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.perfilciudadano.R
import com.example.perfilciudadano.adapters.OptionsAdapter
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.viewmodel.ColoniesViewModel
import com.example.perfilciudadano.viewmodel.SectionsViewModel
import com.example.perfilciudadano.viewmodel.ZipCodesViewModel

class NewPollFormFragment : Fragment() {
  private val sectionsViewModel: SectionsViewModel by activityViewModels()
  private val zipCodesViewModel: ZipCodesViewModel by activityViewModels()
  private val coloniesViewModel: ColoniesViewModel by activityViewModels()
  private var sections: List<Option> = emptyList();
  private var zipCodes: List<Option> = emptyList()
  private var colonies: List<Option> = emptyList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sectionsViewModel.sections.observe(this, Observer { item ->
      sections = item
    })
    zipCodesViewModel.zipCodes.observe(this, Observer { item ->
      zipCodes = item
    })
    coloniesViewModel.colonies.observe(this, Observer { item ->
      colonies = item
    })
  }

  fun setupZipCodeInput(view: View) {
    val zipCodeInput = view.findViewById<EditText>(R.id.etZipCode)
    zipCodeInput.setOnClickListener {
      val dialog = Dialog(requireContext())
      dialog.setContentView(R.layout.searchable_list)
      dialog.window?.setLayout(650, 800)
      dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      dialog.show()
      val searchText: EditText = dialog.findViewById(R.id.searchEt)
      val searchList: ListView = dialog.findViewById(R.id.searchList)
      val adapter = OptionsAdapter(requireContext(), R.layout.option_layout, zipCodes)
      searchList.adapter = adapter
      searchText.doOnTextChanged { text, start, before, count ->
        adapter.filter.filter(text)
      }
      searchList.setOnItemClickListener { adapterView, view, i, l ->
        zipCodeInput.setText(adapter.getItem(i)?.value)
        adapter.getItem(i)?.let { it -> zipCodesViewModel.selectZipCode(it) }
        dialog.dismiss()
      }
    }
  }

  fun setupSectionInput(view: View) {
    val sectionInput = view.findViewById<EditText>(R.id.etSection)
    sectionInput.setOnClickListener {
      val dialog = Dialog(requireContext())
      dialog.setContentView(R.layout.searchable_list)
      dialog.window?.setLayout(650, 800)
      dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      dialog.show()
      val searchText: EditText = dialog.findViewById(R.id.searchEt)
      val searchList: ListView = dialog.findViewById(R.id.searchList)
      val adapter = OptionsAdapter(requireContext(), R.layout.option_layout, sections)
      searchList.adapter = adapter
      searchText.doOnTextChanged { text, start, before, count ->
        adapter.filter.filter(text)
      }
      searchList.setOnItemClickListener { adapterView, view, i, l ->
        sectionInput.setText(adapter.getItem(i)?.value)
        adapter.getItem(i)?.let { it -> sectionsViewModel.selectSection(it) }
        dialog.dismiss()
      }
    }
  }

  fun setupColonyInput(view: View) {
    val colonyInput = view.findViewById<EditText>(R.id.etColony)
    colonyInput.setOnClickListener {
      val dialog = Dialog(requireContext())
      dialog.setContentView(R.layout.searchable_list)
      dialog.window?.setLayout(650, 800)
      dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      dialog.show()
      val searchText: EditText = dialog.findViewById(R.id.searchEt)
      val searchList: ListView = dialog.findViewById(R.id.searchList)
      val adapter = OptionsAdapter(requireContext(), R.layout.option_layout, colonies)
      searchList.adapter = adapter
      searchText.doOnTextChanged { text, start, before, count ->
        adapter.filter.filter(text)
      }
      searchList.setOnItemClickListener { adapterView, view, i, l ->
        colonyInput.setText(adapter.getItem(i)?.value)
        adapter.getItem(i)?.let { it -> coloniesViewModel.selectColony(it) }
        dialog.dismiss()
      }
    }
  }

  fun setupGenderInput(view: View) {
    val maleRadio: RadioButton = view.findViewById(R.id.rMale)
    val femaleRadio: RadioButton = view.findViewById(R.id.rFemale)
    maleRadio.isChecked = true
    maleRadio.setOnClickListener {
      femaleRadio.isChecked = false
      maleRadio.isChecked = true
    }
    femaleRadio.setOnClickListener {
      femaleRadio.isChecked = true
      maleRadio.isChecked = false
    }
  }

  fun setupIneLivingAddressInput(view: View) {
    val yesRadio: RadioButton = view.findViewById(R.id.rIneMatchesLivingAddress)
    val noRadio: RadioButton = view.findViewById(R.id.rIneDoesntMatchesLivingAddress)
    val ineLivingAddressInput: EditText = view.findViewById(R.id.etIneLivingAddress)
    ineLivingAddressInput.visibility = View.GONE

    yesRadio.setOnClickListener {
      ineLivingAddressInput.visibility = View.GONE
      ineLivingAddressInput.setText("")
    }
    noRadio.setOnClickListener {
      ineLivingAddressInput.visibility = View.VISIBLE
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view: View = inflater.inflate(R.layout.fragment_new_poll_form, container, false)
    // TODO: Implement elector key masking with regex or length
    // TODO: Implement CURP masking with regex
    // TODO: Implement date picker for BirthDate
    setupSectionInput(view)
    setupZipCodeInput(view)
    setupColonyInput(view)
    setupGenderInput(view)
    setupIneLivingAddressInput(view)
    return view
  }
}