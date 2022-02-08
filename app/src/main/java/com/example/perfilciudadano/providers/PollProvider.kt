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
import com.example.perfilciudadano.services.validations
import com.example.perfilciudadano.viewmodel.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class PollProvider {
  companion object {
    private var poll: Poll = Poll()
    private const val OtherOption = "Otro (especificar)"

    fun bindElectorKeyInput(view: View, pollViewModel: PollViewModel) {
      val electorKeyInput = view.findViewById<TextInputEditText>(R.id.etElectorKey)
      electorKeyInput.doOnTextChanged { text, start, before, count ->
        poll.ElectorKey = text.toString()
        pollViewModel.updatePoll(poll)
        validations.validateElectorKeyInput(text.toString(), view.findViewById(R.id.loElectorKey), pollViewModel, "ElectorKey")
      }
    }

    fun bindCurpInput(view: View, pollViewModel: PollViewModel) {
      val curpInput = view.findViewById<EditText>(R.id.etCurp)
      curpInput.doOnTextChanged { text, start, before, count ->
        poll.Curp = text.toString()
        pollViewModel.updatePoll(poll)
        validations.validateCurpInput(text.toString(), view.findViewById(R.id.loCurp), pollViewModel, "Curp")
      }
    }

    fun bindNameInput(view: View, pollViewModel: PollViewModel) {
      val nameInput = view.findViewById<EditText>(R.id.etName)
      nameInput.doOnTextChanged { text, start, before, count ->
        poll.Name = text.toString()
        pollViewModel.updatePoll(poll)
        validations.validateName(text.toString(), view.findViewById(R.id.loName), pollViewModel, "Name")
      }
    }

    fun bindSurNameInput(view: View, pollViewModel: PollViewModel) {
      val surNameInput = view.findViewById<EditText>(R.id.etSurname)
      surNameInput.doOnTextChanged { text, start, before, count ->
        poll.SurName = text.toString()
        pollViewModel.updatePoll(poll)
        validations.validateSurName(text.toString(), view.findViewById(R.id.loSurName), pollViewModel, "SurName")
      }
    }

    fun bindSecondSurNameInput(view: View, pollViewModel: PollViewModel) {
      val secondSurNameInput = view.findViewById<EditText>(R.id.etSecondSurname)
      secondSurNameInput.doOnTextChanged { text, start, before, count ->
        poll.SecondSurName = text.toString()
        pollViewModel.updatePoll(poll)
        validations.validateSecondSurName(text.toString(), view.findViewById(R.id.loSecondSurName), pollViewModel, "SecondSurName")
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
        validations.validateBirthPlace(text.toString(), view.findViewById(R.id.loBirthPlace), pollViewModel, "BirthPlace")
      }
    }

    fun bindIneExpirationYearInput(view: View, pollViewModel: PollViewModel) {
      val ineExpirationYearInput = view.findViewById<EditText>(R.id.etIneExp)
      ineExpirationYearInput.doOnTextChanged { text, start, before, count ->
        if (!text.toString().isNullOrEmpty()) {
          poll.IneExpirationYear = text.toString().toInt()
        } else {
          poll.IneExpirationYear = -1
        }
        pollViewModel.updatePoll(poll)
        validations.validateIneExpirationYear(text.toString(), view.findViewById(R.id.loIneExp), pollViewModel, "IneExpirationYear")
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

    fun bindZipCodeInput(view: View, pollViewModel: PollViewModel, optionsViewModel: OptionsViewModel) {
      val input = view.findViewById<EditText>(R.id.etZipCode)
      val colonyInput = view.findViewById<EditText>(R.id.etColony)
      input.doOnTextChanged { text, start, before, count ->
        poll.ZipCode = text.toString()
        validations.validateZipCodeInput(text.toString(), view.findViewById(R.id.loZipCode), pollViewModel, "ZipCode")
        if (text.toString().length == 5) {
          optionsViewModel.getColoniesByZipCode(text.toString())
        } else {
          optionsViewModel.clearColonies()
          colonyInput.setText("")
          poll.Colony = -1
        }
        pollViewModel.updatePoll(poll)
      }
    }

    fun bindSectionInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
    ) {
      val sectionInput = view.findViewById<EditText>(R.id.etSection)
      val layout = view.findViewById<TextInputLayout>(R.id.loSection)
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
        dialog.setOnDismissListener {
          validations.validateSelectionInput(poll.Section, layout, pollViewModel, "Section")
        }
        searchText.doOnTextChanged { text, start, before, count ->
          adapter.filter.filter(text)
        }
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          val item = adapter.getItem(i)
          item?.let {
            sectionInput.setText(item.name)
            poll.Section = item.id
            pollViewModel.updatePoll(poll)
            validations.validateSelectionInput(poll.Section, layout, pollViewModel, "Section")
            dialog.dismiss()
          }
        }
      }
    }

    fun bindColonyInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
    ) {
      val colonyInput = view.findViewById<EditText>(R.id.etColony)
      val layout = view.findViewById<TextInputLayout>(R.id.loColony)
      colonyInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        dialog.setOnDismissListener {
          validations.validateSelectionInput(poll.Colony, layout, pollViewModel, "Colony")
        }
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          val item = adapter.getItem(i)
          item?.let {
            colonyInput.setText(item.name)
            poll.Colony = item.id
            pollViewModel.updatePoll(poll)
            validations.validateSelectionInput(poll.Colony, layout, pollViewModel, "Colony")
            dialog.dismiss()
          }
        }
      }
    }

    fun bindStreetInput(view: View, pollViewModel: PollViewModel) {
      val streetInput = view.findViewById<EditText>(R.id.etStreet)
      streetInput.doOnTextChanged { text, start, before, count ->
        poll.Street = text.toString()
        pollViewModel.updatePoll(poll)
        validations.validateStreet(text.toString(), view.findViewById(R.id.loStreet), pollViewModel, "Street")
      }
    }

    fun bindExteriorNumberInput(view: View, pollViewModel: PollViewModel) {
      val exteriorNumberInput = view.findViewById<EditText>(R.id.etExtNum)
      exteriorNumberInput.doOnTextChanged { text, start, before, count ->
        poll.ExteriorNumber = text.toString()
        pollViewModel.updatePoll(poll)
        validations.validateExteriorNumberInput(text.toString(), view.findViewById(R.id.loExtNum), pollViewModel, "ExteriorNumber")
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
    ) {
      val maritalStatusInput = view.findViewById<EditText>(R.id.etMaritalStatus)
      val layout = view.findViewById<TextInputLayout>(R.id.loMaritalStatus)
      maritalStatusInput.setOnClickListener {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.unsearchable_list)
        dialog.window?.setLayout(650, 800)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val searchList: ListView = dialog.findViewById(R.id.searchList)
        val adapter = OptionsAdapter(context, R.layout.option_layout, data)
        searchList.adapter = adapter
        dialog.setOnDismissListener {
          validations.validateSelectionInput(poll.MaritalStatus, layout, pollViewModel, "MaritalStatus")
        }
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          val item = adapter.getItem(i)
          item?.let {
            maritalStatusInput.setText(item.name)
            poll.MaritalStatus = item.id
            pollViewModel.updatePoll(poll)
            validations.validateSelectionInput(poll.MaritalStatus, layout, pollViewModel, "MaritalStatus")
            dialog.dismiss()
          }
        }
      }
    }

    fun bindFamilyPositionsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
    ) {
      val familyPositionsInput = view.findViewById<EditText>(R.id.etFamilyPosition)
      val layout = view.findViewById<TextInputLayout>(R.id.loFamilyPosition)
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
        dialog.setOnDismissListener {
          validations.validateSelectionInput(poll.FamilyPosition, layout, pollViewModel, "FamilyPosition")
        }
        searchList.setOnItemClickListener { adapterView, view, i, l ->
          val item = adapter.getItem(i)
          item?.let {
            familyPositionsInput.setText(item.name)
            poll.FamilyPosition = item.id
            pollViewModel.updatePoll(poll)
            if (item.name == OtherOption) {
              otherFamilyPositionInput.visibility = View.VISIBLE
            } else {
              otherFamilyPositionInput.visibility = View.GONE
            }
            validations.validateSelectionInput(poll.FamilyPosition, layout, pollViewModel, "FamilyPosition")
            dialog.dismiss()
          }
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
        if (text.toString().isNullOrEmpty()) {
          poll.CellPhoneNumber = -1
        } else {
          poll.CellPhoneNumber = text.toString().toLong()
        }
        pollViewModel.updatePoll(poll)
        validations.validateCellPhoneNumberInput(text.toString(), view.findViewById(R.id.loCellPhoneNumber), pollViewModel, "CellPhoneNumber")
      }
    }

    fun bindPhoneNumberInput(view: View, pollViewModel: PollViewModel) {
      val phoneNumberInput = view.findViewById<EditText>(R.id.etPhoneNumber)
      phoneNumberInput.doOnTextChanged { text, start, before, count ->
        if (text.toString().isNullOrEmpty()) {
         poll.CellPhoneNumber = -1
        } else {
          poll.PhoneNumber = text.toString().toLong()
        }
        pollViewModel.updatePoll(poll)
        validations.validatePhoneNumberInput(text.toString(), view.findViewById(R.id.loPhoneNumber), pollViewModel, "PhoneNumber")
      }
    }

    fun bindFamilyIntegrantsNumberInput(view: View, pollViewModel: PollViewModel) {
      val phoneNumberInput = view.findViewById<EditText>(R.id.etFamilyIntegrantsNumber)
      phoneNumberInput.doOnTextChanged { text, start, before, count ->
        if (text.toString().isNullOrEmpty()) {
          poll.FamilyIntegrantsNumber = -1
        } else {
          poll.FamilyIntegrantsNumber = text.toString().toInt()
        }
        pollViewModel.updatePoll(poll)
        validations.validateFamilyIntegrantsNumberInput(text.toString(), view.findViewById(R.id.loFamilyIntegrantsNumber), pollViewModel, "FamilyIntegrantsNumber")
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
          val item = adapter.getItem(i)
          item?.let {
            studyDegreeInput.setText(item.name)
            poll.StudyDegree = item.id
            pollViewModel.updatePoll(poll)
            dialog.dismiss()
          }
        }
      }
    }

    fun bindOccupationsInput(
      context: Context,
      view: View,
      data: List<Option>,
      pollViewModel: PollViewModel,
    ) {
      val occupationsInput = view.findViewById<EditText>(R.id.etOccupation)
      val layout = view.findViewById<TextInputLayout>(R.id.loOtherOccupation)
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
          val item = adapter.getItem(i)
          item?.let {
            occupationsInput.setText(item.name)
            poll.Occupation = item.id
            pollViewModel.updatePoll(poll)
            if (item.isOtherOption) {
              layout.visibility = View.VISIBLE
            } else {
              layout.visibility = View.GONE
            }
            dialog.dismiss()
          }
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
    ) {
      val mobilityMethodsInput = view.findViewById<EditText>(R.id.etMobilityMethod)
      val layout = view.findViewById<TextInputLayout>(R.id.loOtherMobilityMethod)
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
          val item = adapter.getItem(i)
          item?.let {
            mobilityMethodsInput.setText(item.name)
            poll.MobilityMethod = item.id
            pollViewModel.updatePoll(poll)
            if (item.isOtherOption) {
              layout.visibility = View.VISIBLE
            } else {
              layout.visibility = View.GONE
            }
            dialog.dismiss()
          }
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
    ) {
      val diseasesInput = view.findViewById<EditText>(R.id.etDiseases)
      val otherDiseasesInput = view.findViewById<TextInputLayout>(R.id.loOtherDiseases)
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
          val item = adapter.getItem(i)
          item?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.Diseases = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            diseasesInput.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.isOtherOption }
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
    ) {
      val federalSupportsInput = view.findViewById<EditText>(R.id.etFederalSupports)
      val otherFederalSupportsInput = view.findViewById<TextInputLayout>(R.id.loOtherFederalSupport)
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
          val item = adapter.getItem(i)
          item?.let { it ->
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.FederalSupports = selectedOptions.map { selectedOption -> selectedOption.id }
            pollViewModel.updatePoll(poll)
            federalSupportsInput.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.isOtherOption }
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
    ) {
      val stateSupportsInput = view.findViewById<EditText>(R.id.etStateSupport)
      val otherStateSupportsInput = view.findViewById<TextInputLayout>(R.id.loOtherStateSupport)
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
          val item = adapter.getItem(i)
          item?.let {
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.StateSupports = selectedOptions.map { it.id }
            pollViewModel.updatePoll(poll)
            stateSupportsInput.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.isOtherOption }
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
    ) {
      val municipalSupportsInput = view.findViewById<EditText>(R.id.etMunicipalSupport)
      val otherMunicipalSupportsInput = view.findViewById<TextInputLayout>(R.id.loOtherMunicipalSupport)
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
          val item = adapter.getItem(i)
          item?.let {
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.MunicipalSupports = selectedOptions.map { it.id }
            pollViewModel.updatePoll(poll)
            municipalSupportsInput.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.isOtherOption }
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
    ) {
      val input = view.findViewById<EditText>(R.id.etHobbies)
      val otherInput = view.findViewById<TextInputLayout>(R.id.loOtherHobbies)
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
          val item = adapter.getItem(i)
          item?.let {
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.Hobbies = selectedOptions.map { it.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected =
            selectedOptions.any { it.isOtherOption }
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
    ) {
      val input = view.findViewById<EditText>(R.id.etReligions)
      val otherInput = view.findViewById<TextInputLayout>(R.id.loOtherReligion)
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
          val item = adapter.getItem(i)
          item?.let {
            input.setText(it.name)
            poll.Religion = it.id
            pollViewModel.updatePoll(poll)
            if (it.isOtherOption) {
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
    ) {
      val input = view.findViewById<EditText>(R.id.etSports)
      val otherInput = view.findViewById<TextInputLayout>(R.id.loOtherSport)
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
          val item = adapter.getItem(i)
          item?.let {
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.Sports = selectedOptions.map { it.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.isOtherOption }
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
          val item = adapter.getItem(i)
          item?.let {
            input.setText(it.name)
            poll.SoccerTeam = it.id
            pollViewModel.updatePoll(poll)
            dialog.dismiss()
          }
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
    ) {
      val input = view.findViewById<EditText>(R.id.etPetType)
      val otherInput = view.findViewById<TextInputLayout>(R.id.loOtherPetType)
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
          val item = adapter.getItem(i)
          item?.let {
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.PetTypes = selectedOptions.map { it.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.isOtherOption }
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
    ) {
      val input = view.findViewById<EditText>(R.id.etGovernmentInvitationActivityOrThemes)
      val otherInput = view.findViewById<TextInputLayout>(R.id.loOtherGovernmentInvitationActivityOrThemes)
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
          val item = adapter.getItem(i)
          item?.let {
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.GovernmentInvitationActivityOrThemes = selectedOptions.map { it.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { it.name })
          }
          val isOtherSelected = selectedOptions.any { it.isOtherOption }
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
          val item = adapter.getItem(i)
          item?.let {
            val itemIndex = selectedOptions.indexOf(it)
            if (itemIndex == -1) {
              selectedOptions.add(it)
            } else {
              selectedOptions.removeAt(itemIndex)
            }
            poll.GovernmentTaskActivityOrThemes = selectedOptions.map { it.id }
            pollViewModel.updatePoll(poll)
            input.setText(selectedOptions.joinToString(", ") { it.name })
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
          val item = adapter.getItem(i)
          item?.let {
            input.setText(it.name)
            poll.PollutionCause = it.id
            pollViewModel.updatePoll(poll)
            dialog.dismiss()
          }
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