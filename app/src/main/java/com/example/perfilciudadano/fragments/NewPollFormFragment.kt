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
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.perfilciudadano.R
import com.example.perfilciudadano.adapters.OptionsAdapter
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.viewmodel.SectionsViewModel

class NewPollFormFragment : Fragment() {
  private val sectionsViewModel: SectionsViewModel by activityViewModels()
  private lateinit var sectionInput: EditText
  private var sections: List<Option> = emptyList();

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sectionsViewModel.sections.observe(this, Observer { item ->
      sections = item
    })
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view: View = inflater.inflate(R.layout.fragment_new_poll_form, container, false)
    sectionInput = view.findViewById(R.id.etSection)
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
    return view
  }
}