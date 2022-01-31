package com.example.perfilciudadano.providers

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import androidx.core.widget.doOnTextChanged
import com.example.perfilciudadano.R
import com.example.perfilciudadano.adapters.OptionsAdapter
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.models.Poll
import com.example.perfilciudadano.viewmodel.*

class PollProvider {
  companion object {
    private var poll: Poll = Poll()
    private const val OtherOption = "Otro (especificar)"

    fun bindElectorKeyInput(view: View, pollViewModel: PollViewModel) {
      val electorKeyInput = view.findViewById<EditText>(R.id.etElectorKey)
      electorKeyInput.doOnTextChanged { text, start, before, count ->
        poll.ElectorKey = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindCurpInput(view: View, pollViewModel: PollViewModel) {
      val curpInput = view.findViewById<EditText>(R.id.etCurp)
      curpInput.doOnTextChanged { text, start, before, count ->
        poll.Curp = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindNameInput(view: View, pollViewModel: PollViewModel) {
      val nameInput = view.findViewById<EditText>(R.id.etName)
      nameInput.doOnTextChanged { text, start, before, count ->
        poll.Name = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindSurNameInput(view: View, pollViewModel: PollViewModel) {
      val surNameInput = view.findViewById<EditText>(R.id.etSurname)
      surNameInput.doOnTextChanged { text, start, before, count ->
        poll.SurName = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindSecondSurNameInput(view: View, pollViewModel: PollViewModel) {
      val secondSurNameInput = view.findViewById<EditText>(R.id.etSecondSurname)
      secondSurNameInput.doOnTextChanged { text, start, before, count ->
        poll.SecondSurName = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindBirthDateInput(view: View, pollViewModel: PollViewModel) {
      val birthDateInput = view.findViewById<EditText>(R.id.etBirthDate)
      birthDateInput.doOnTextChanged { text, start, before, count ->
        poll.BirthDate = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindBirthPlaceInput(view: View, pollViewModel: PollViewModel) {
      val birthPlaceInput = view.findViewById<EditText>(R.id.etBirthPlace)
      birthPlaceInput.doOnTextChanged { text, start, before, count ->
        poll.BirthPlace = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindIneExpirationYearInput(view: View, pollViewModel: PollViewModel) {
      val ineExpirationYearInput = view.findViewById<EditText>(R.id.etIneExp)
      ineExpirationYearInput.doOnTextChanged { text, start, before, count ->
        poll.IneExpirationYear = text.toString().toInt()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindGenderInput(view: View, pollViewModel: PollViewModel) {
      val maleRadio: RadioButton = view.findViewById(R.id.rMale)
      val femaleRadio: RadioButton = view.findViewById(R.id.rFemale)
      maleRadio.isChecked = true

      maleRadio.setOnClickListener {
        poll.Gender = "M"
        pollViewModel.updatePoll(poll)
      }
      femaleRadio.setOnClickListener {
        poll.Gender = "F"
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindZipCodeInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      zipCodesViewModel: ZipCodesViewModel
    ) {
      val zipCodeInput = view.findViewById<EditText>(R.id.etZipCode)
      zipCodeInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.searchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchText: EditText = dialog.findViewById(R.id.searchEt)
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchText.doOnTextChanged { text, start, before, count ->
          adapter.filter.filter(text)
        }
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          zipCodeInput.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> zipCodesViewModel.selectZipCode(it) }
          poll.ZipCode = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          dialog.dismiss()
        }
      }
    }

    fun bindSectionInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      sectionsViewModel: SectionsViewModel
    ) {
      val sectionInput = view.findViewById<EditText>(R.id.etSection)
      sectionInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.searchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchText: EditText = dialog.findViewById(R.id.searchEt)
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchText.doOnTextChanged { text, start, before, count ->
          adapter.filter.filter(text)
        }
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          sectionInput.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> sectionsViewModel.selectSection(it) }
          poll.Section = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          dialog.dismiss()
        }
      }
    }

    fun bindColonyInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      coloniesViewModel: ColoniesViewModel
    ) {
      val colonyInput = view.findViewById<EditText>(R.id.etColony)
      colonyInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          colonyInput.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> coloniesViewModel.selectColony(it) }
          poll.Colony = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          dialog.dismiss()
        }
      }
    }

    fun bindStreetInput(view: View, pollViewModel: PollViewModel) {
      val streetInput = view.findViewById<EditText>(R.id.etStreet)
      streetInput.doOnTextChanged { text, start, before, count ->
        poll.Street = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindExteriorNumberInput(view: View, pollViewModel: PollViewModel) {
      val exteriorNumberInput = view.findViewById<EditText>(R.id.etExtNum)
      exteriorNumberInput.doOnTextChanged { text, start, before, count ->
        poll.ExteriorNumber = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindInteriorNumberInput(view: View, pollViewModel: PollViewModel) {
      val interiorNumberInput = view.findViewById<EditText>(R.id.etIntNum)
      interiorNumberInput.doOnTextChanged { text, start, before, count ->
        poll.InteriorNumber = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindIneLivingAddressInput(view: View, pollViewModel: PollViewModel) {
      val yesRadio: RadioButton = view.findViewById(R.id.rIneMatchesLivingAddress)
      val noRadio: RadioButton = view.findViewById(R.id.rIneDoesntMatchesLivingAddress)
      val ineLivingAddressInput: EditText = view.findViewById(R.id.etIneLivingAddress)
      yesRadio.isChecked = true

      yesRadio.setOnClickListener {
        ineLivingAddressInput.visibility = View.GONE
        ineLivingAddressInput.setText("")
        poll.IneMatchesLivingAddress = true
        pollViewModel.updatePoll(poll)
      }
      noRadio.setOnClickListener {
        ineLivingAddressInput.visibility = View.VISIBLE
        poll.IneMatchesLivingAddress = false
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindMaritalStatusInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      maritalStatusesViewModel: MaritalStatusesViewModel
    ) {
      val maritalStatusInput = view.findViewById<EditText>(R.id.etMaritalStatus)
      maritalStatusInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          maritalStatusInput.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> maritalStatusesViewModel.selectMaritalStatus(it) }
          poll.MaritalStatus = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          dialog.dismiss()
        }
      }
    }

    fun bindFamilyPositionsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      familyPositionsViewModel: FamilyPositionsViewModel
    ) {
      val familyPositionsInput = view.findViewById<EditText>(R.id.etFamilyPosition)
      val otherFamilyPositionInput = view.findViewById<EditText>(R.id.etOtherFamilyPosition)
      familyPositionsInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          familyPositionsInput.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> familyPositionsViewModel.selectFamilyPosition(it) }
          poll.FamilyPosition = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          if (adapter.getItem(i)?.name == OtherOption) {
            otherFamilyPositionInput.visibility = View.VISIBLE
          } else {
            otherFamilyPositionInput.visibility = View.GONE
          }
          dialog.dismiss()
        }
      }
    }

    fun bindOtherFamilyPositionInput(view: View, pollViewModel: PollViewModel) {
      val otherFamilyPositionInput = view.findViewById<EditText>(R.id.etOtherFamilyPosition)
      otherFamilyPositionInput.doOnTextChanged { text, start, before, count ->
        poll.OtherFamilyPosition = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindCellPhoneNumberInput(view: View, pollViewModel: PollViewModel) {
      val cellPhoneNumberInput = view.findViewById<EditText>(R.id.etCellPhoneNumber)
      cellPhoneNumberInput.doOnTextChanged { text, start, before, count ->
        poll.CellPhoneNumber = text.toString().toLong()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindPhoneNumberInput(view: View, pollViewModel: PollViewModel) {
      val phoneNumberInput = view.findViewById<EditText>(R.id.etPhoneNumber)
      phoneNumberInput.doOnTextChanged { text, start, before, count ->
        poll.PhoneNumber = text.toString().toLong()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindFamilyIntegrantsNumberInput(view: View, pollViewModel: PollViewModel) {
      val phoneNumberInput = view.findViewById<EditText>(R.id.etFamilyIntegrantsNumber)
      phoneNumberInput.doOnTextChanged { text, start, before, count ->
        poll.FamilyIntegrantsNumber = text.toString().toInt()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindHouseHasInternetInput(view: View, pollViewModel: PollViewModel) {
      val yesRadio: RadioButton = view.findViewById(R.id.rHouseHasInternet)
      val noRadio: RadioButton = view.findViewById(R.id.rHouseDoesntHasInternet)

      yesRadio.setOnClickListener {
        poll.HouseHasInternet = true
        pollViewModel.updatePoll(poll)
      }
      noRadio.setOnClickListener {
        poll.HouseHasInternet = false
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindStudyDegreeInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      studyDegreesViewModel: StudyDegreesViewModel
    ) {
      val studyDegreeInput = view.findViewById<EditText>(R.id.etStudyDegree)
      studyDegreeInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          studyDegreeInput.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> studyDegreesViewModel.selectStudyDegree(it) }
          poll.StudyDegree = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          dialog.dismiss()
        }
      }
    }

    fun bindOccupationsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      occupationsViewModel: OccupationsViewModel
    ) {
      val occupationsInput = view.findViewById<EditText>(R.id.etOccupation)
      val otherOccupationInput = view.findViewById<EditText>(R.id.etOtherOccupation)
      occupationsInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          occupationsInput.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> occupationsViewModel.selectOccupation(it) }
          poll.Occupation = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          if (adapter.getItem(i)?.name == OtherOption) {
            otherOccupationInput.visibility = View.VISIBLE
          } else {
            otherOccupationInput.visibility = View.GONE
          }
          dialog.dismiss()
        }
      }
    }

    fun bindOtherOccupationInput(view: View, pollViewModel: PollViewModel) {
      val otherOccupationInput = view.findViewById<EditText>(R.id.etOtherOccupation)
      otherOccupationInput.doOnTextChanged { text, start, before, count ->
        poll.OtherOccupation = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindMobilityMethodsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      mobilityMethodsViewModel: MobilityMethodsViewModel
    ) {
      val mobilityMethodsInput = view.findViewById<EditText>(R.id.etMobilityMethod)
      val otherMobilityMethodInput = view.findViewById<EditText>(R.id.etOtherMobilityMethod)
      mobilityMethodsInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          mobilityMethodsInput.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> mobilityMethodsViewModel.selectMobilityMethod(it) }
          poll.MobilityMethod = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          if (adapter.getItem(i)?.name == OtherOption) {
            otherMobilityMethodInput.visibility = View.VISIBLE
          } else {
            otherMobilityMethodInput.visibility = View.GONE
          }
          dialog.dismiss()
        }
      }
    }

    fun bindOtherMobilityMethodInput(view: View, pollViewModel: PollViewModel) {
      val otherMobilityMethodInput = view.findViewById<EditText>(R.id.etOtherMobilityMethod)
      otherMobilityMethodInput.doOnTextChanged { text, start, before, count ->
        poll.OtherMobilityMethod = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindDiseasesInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      diseasesViewModel: DiseasesViewModel
    ) {
      val diseasesInput = view.findViewById<EditText>(R.id.etDiseases)
      val otherDiseasesInput = view.findViewById<EditText>(R.id.etOtherDiseases)
      val selectedOptions: MutableList<Option> = mutableListOf()
      diseasesInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            diseasesViewModel.selectDiseases(selectedOptions)
            poll.Diseases = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            diseasesInput.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.name == OtherOption }
          if (isOtherSelected) {
            otherDiseasesInput.visibility = View.VISIBLE
          } else {
            otherDiseasesInput.visibility = View.GONE
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindOtherDiseasesInput(view: View, pollViewModel: PollViewModel) {
      val otherDiseasesInput = view.findViewById<EditText>(R.id.etOtherDiseases)
      otherDiseasesInput.doOnTextChanged { text, start, before, count ->
        poll.OtherDiseases = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindFederalSupportsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      federalSupportsViewModel: FederalSupportsViewModel
    ) {
      val federalSupportsInput = view.findViewById<EditText>(R.id.etFederalSupports)
      val otherFederalSupportsInput = view.findViewById<EditText>(R.id.etOtherFederalSupport)
      val selectedOptions: MutableList<Option> = mutableListOf()
      federalSupportsInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            federalSupportsViewModel.selectFederalSupports(selectedOptions)
            poll.FederalSupports = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            federalSupportsInput.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.name == OtherOption }
          if (isOtherSelected) {
            otherFederalSupportsInput.visibility = View.VISIBLE
          } else {
            otherFederalSupportsInput.visibility = View.GONE
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindOtherFederalSupportInput(view: View, pollViewModel: PollViewModel) {
      val otherFederalSupport = view.findViewById<EditText>(R.id.etOtherFederalSupport)
      otherFederalSupport.doOnTextChanged { text, start, before, count ->
        poll.OtherFederalSupport = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindStateSupportsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      stateSupportsViewModel: StateSupportsViewModel
    ) {
      val stateSupportsInput = view.findViewById<EditText>(R.id.etStateSupport)
      val otherStateSupportsInput = view.findViewById<EditText>(R.id.etOtherStateSupport)
      val selectedOptions: MutableList<Option> = mutableListOf()
      stateSupportsInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            stateSupportsViewModel.selectStateSupports(selectedOptions)
            poll.StateSupports = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            stateSupportsInput.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.name == OtherOption }
          if (isOtherSelected) {
            otherStateSupportsInput.visibility = View.VISIBLE
          } else {
            otherStateSupportsInput.visibility = View.GONE
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindOtherStateSupportInput(view: View, pollViewModel: PollViewModel) {
      val otherStateSupportInput = view.findViewById<EditText>(R.id.etOtherStateSupport)
      otherStateSupportInput.doOnTextChanged { text, start, before, count ->
        poll.OtherStateSupport = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindMunicipalSupportsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      municipalSupportsViewModel: MunicipalSupportsViewModel
    ) {
      val municipalSupportsInput = view.findViewById<EditText>(R.id.etMunicipalSupport)
      val otherMunicipalSupportsInput = view.findViewById<EditText>(R.id.etOtherMunicipalSupport)
      val selectedOptions: MutableList<Option> = mutableListOf()
      municipalSupportsInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            municipalSupportsViewModel.selectMunicipalSupports(selectedOptions)
            poll.MunicipalSupports = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            municipalSupportsInput.setText(selectedOptions.joinToString(", ") { option -> option.name })
          }
          val isOtherSelected =
            selectedOptions.any { selectedOption -> selectedOption.name == OtherOption }
          if (isOtherSelected) {
            otherMunicipalSupportsInput.visibility = View.VISIBLE
          } else {
            otherMunicipalSupportsInput.visibility = View.GONE
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindOtherMunicipalSupportInput(view: View, pollViewModel: PollViewModel) {
      val otherMunicipalSupportInput = view.findViewById<EditText>(R.id.etOtherMunicipalSupport)
      otherMunicipalSupportInput.doOnTextChanged { text, start, before, count ->
        poll.OtherMunicipalSupport = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindHobbiesInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      hobbiesViewModel: HobbiesViewModel
    ) {
      val input = view.findViewById<EditText>(R.id.etHobbies)
      val otherInput = view.findViewById<EditText>(R.id.etOtherHobbies)
      val selectedOptions: MutableList<Option> = mutableListOf()
      input.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            hobbiesViewModel.selectHobbies(selectedOptions)
            poll.Hobbies = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { option -> option.name })
          }
          val isOtherSelected =
            selectedOptions.any { selectedOption -> selectedOption.name == OtherOption }
          if (isOtherSelected) {
            otherInput.visibility = View.VISIBLE
          } else {
            otherInput.visibility = View.GONE
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindOtherHobbiesInput(view: View, pollViewModel: PollViewModel) {
      val otherHobbiesInput = view.findViewById<EditText>(R.id.etOtherHobbies)
      otherHobbiesInput.doOnTextChanged { text, start, before, count ->
        poll.OtherHobbies = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindReligionsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      religionsViewModel: ReligionsViewModel
    ) {
      val input = view.findViewById<EditText>(R.id.etReligions)
      val otherInput = view.findViewById<EditText>(R.id.etOtherReligion)
      input.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          input.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it ->
            poll.Religion = it.id
            pollViewModel.updatePoll(poll)
            religionsViewModel.selectReligion(it)
            if (it.name == OtherOption) {
              otherInput.visibility = View.VISIBLE
            } else {
              otherInput.visibility = View.GONE
            }
          }
          dialog.dismiss()
        }
      }
    }

    fun bindOtherReligionInput(view: View, pollViewModel: PollViewModel) {
      val otherInput = view.findViewById<EditText>(R.id.etOtherReligion)
      otherInput.doOnTextChanged { text, start, before, count ->
        poll.OtherReligion = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindSportsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      sportsViewModel: SportsViewModel
    ) {
      val input = view.findViewById<EditText>(R.id.etSports)
      val otherInput = view.findViewById<EditText>(R.id.etOtherSport)
      val selectedOptions: MutableList<Option> = mutableListOf()
      input.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            sportsViewModel.selectSports(selectedOptions)
            poll.Sports = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { option -> option.name })
          }
          val isOtherSelected =
            selectedOptions.any { selectedOption -> selectedOption.name == OtherOption }
          if (isOtherSelected) {
            otherInput.visibility = View.VISIBLE
          } else {
            otherInput.visibility = View.GONE
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindOtherSportInput(view: View, pollViewModel: PollViewModel) {
      val otherInput = view.findViewById<EditText>(R.id.etOtherSport)
      otherInput.doOnTextChanged { text, start, before, count ->
        poll.OtherSport = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindSoccerTeamsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      soccerTeamsViewModel: SoccerTeamsViewModel
    ) {
      val input = view.findViewById<EditText>(R.id.etSoccerTeam)
      input.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          input.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it -> soccerTeamsViewModel.selectSoccerTeam(it) }
          poll.SoccerTeam = adapter.getItem(i)?.id ?: -1
          pollViewModel.updatePoll(poll)
          dialog.dismiss()
        }
      }
    }

    fun bindLikesPetsInput(view: View, pollViewModel: PollViewModel) {
      val yesRadio: RadioButton = view.findViewById(R.id.rLikesPets)
      val noRadio: RadioButton = view.findViewById(R.id.rDontLikesPets)
      yesRadio.isChecked = true

      yesRadio.setOnClickListener {
        poll.LikesPets = true
        pollViewModel.updatePoll(poll)
      }
      noRadio.setOnClickListener {
        poll.LikesPets = false
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindHasPetsInput(view: View, pollViewModel: PollViewModel) {
      val yesRadio: RadioButton = view.findViewById(R.id.rHasPets)
      val noRadio: RadioButton = view.findViewById(R.id.rDoesntHasPets)
      yesRadio.isChecked = true

      yesRadio.setOnClickListener {
        poll.HasPets = true
        pollViewModel.updatePoll(poll)
      }
      noRadio.setOnClickListener {
        poll.HasPets = false
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindPetTypesInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      petTypesViewModel: PetTypesViewModel
    ) {
      val input = view.findViewById<EditText>(R.id.etPetType)
      val otherInput = view.findViewById<EditText>(R.id.etOtherPetType)
      val selectedOptions: MutableList<Option> = mutableListOf()
      input.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            petTypesViewModel.selectPetTypes(selectedOptions)
            poll.PetTypes = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { option -> option.name })
          }
          val isOtherSelected =
            selectedOptions.any { selectedOption -> selectedOption.name == OtherOption }
          if (isOtherSelected) {
            otherInput.visibility = View.VISIBLE
          } else {
            otherInput.visibility = View.GONE
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindOtherPetTypeInput(view: View, pollViewModel: PollViewModel) {
      val otherInput = view.findViewById<EditText>(R.id.etOtherPetType)
      otherInput.doOnTextChanged { text, start, before, count ->
        poll.OtherPetType = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindGovernmentInvitationActivityOrThemesInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      governmentInvitationActivityOrThemesViewModel: GovernmentInvitationActivityOrThemesViewModel
    ) {
      val input = view.findViewById<EditText>(R.id.etGovernmentInvitationActivityOrThemes)
      val otherInput = view.findViewById<EditText>(R.id.etOtherGovernmentInvitationActivityOrThemes)
      val selectedOptions: MutableList<Option> = mutableListOf()
      input.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            governmentInvitationActivityOrThemesViewModel.selectOptions(selectedOptions)
            poll.GovernmentInvitationActivityOrThemes = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { option -> option.name })
          }
          val isOtherSelected =
            selectedOptions.any { selectedOption -> selectedOption.name == OtherOption }
          if (isOtherSelected) {
            otherInput.visibility = View.VISIBLE
          } else {
            otherInput.visibility = View.GONE
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindOtherGovernmentInvitationActivityOrThemesInput(view: View, pollViewModel: PollViewModel) {
      val otherInput = view.findViewById<EditText>(R.id.etOtherGovernmentInvitationActivityOrThemes)
      otherInput.doOnTextChanged { text, start, before, count ->
        poll.OtherGovernmentInvitationActivityOrTheme = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindGovernmentTaskActivityOrThemesInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      governmentTaskActivityOrThemesViewModel: GovernmentTaskActivityOrThemesViewModel
    ) {
      val input = view.findViewById<EditText>(R.id.etGovernmentTaskActivityOrThemes)
      val selectedOptions: MutableList<Option> = mutableListOf()
      input.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dismissable_unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val dismissButton = dialog.findViewById<Button>(R.id.dismissButton)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          adapter.getItem(i)?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            governmentTaskActivityOrThemesViewModel.selectOptions(selectedOptions)
            poll.GovernmentTaskActivityOrThemes = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { option -> option.name })
          }
        }
        dismissButton.setOnClickListener {
          dialog.dismiss()
        }
      }
    }

    fun bindPollutionCauseInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
      pollutionCausesViewModel: PollutionCausesViewModel
    ) {
      val input = view.findViewById<EditText>(R.id.etPollutionCause)
      input.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          input.setText(adapter.getItem(i)?.name)
          adapter.getItem(i)?.let { it ->
            poll.Religion = it.id
            pollViewModel.updatePoll(poll)
            pollutionCausesViewModel.selectOption(it)
          }
          dialog.dismiss()
        }
      }
    }

    fun bindPrincipalSectorProblematicInput(view: View, pollViewModel: PollViewModel) {
      val input = view.findViewById<EditText>(R.id.etPrincipalSectorProblematic)
      input.doOnTextChanged { text, start, before, count ->
        poll.PrincipalSectorProblematic = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindFutureFiveYearsInput(view: View, pollViewModel: PollViewModel) {
      val input = view.findViewById<EditText>(R.id.etFutureFiveYears)
      input.doOnTextChanged { text, start, before, count ->
        poll.FutureFiveYears = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindCommentsInput(view: View, pollViewModel: PollViewModel) {
      val input = view.findViewById<EditText>(R.id.etComments)
      input.doOnTextChanged { text, start, before, count ->
        poll.Comments = text.toString()
        pollViewModel.updatePoll(poll)
      }
    }

    fun sendPoll(pollViewModel: PollViewModel) {
      pollViewModel.sendPoll(poll)
    }
  }
}