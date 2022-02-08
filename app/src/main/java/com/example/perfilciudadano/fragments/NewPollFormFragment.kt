package com.example.perfilciudadano.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.perfilciudadano.R
import com.example.perfilciudadano.providers.PollProvider
import com.example.perfilciudadano.viewmodel.*
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.ScanIntentResult


class NewPollFormFragment : Fragment() {
  private lateinit var pollView: View
  private val pollViewModel: PollViewModel by activityViewModels()
  private val optionsViewModel: OptionsViewModel by activityViewModels()

  private val barcodeLauncher = registerForActivityResult(
    ScanContract()
  ) { result: ScanIntentResult ->
    if (result.contents == null) {
      Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
    } else {
      Log.e("SCANNN", result.contents.toString())
      Toast.makeText(requireContext(), "Scanned: " + result.contents, Toast.LENGTH_LONG)
        .show()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    optionsViewModel.colonies.observe(this, {
      PollProvider.bindColonyInput(requireContext(), pollView, it, pollViewModel)
    })
    optionsViewModel.options.observe(this, { items ->
      PollProvider.bindSectionInput(requireContext(), pollView, items.filter { it.type == "Section" }, pollViewModel)
      PollProvider.bindMaritalStatusInput(requireContext(), pollView, items.filter { it.type == "MaritalStatus" }, pollViewModel)
      PollProvider.bindFamilyPositionsInput(requireContext(), pollView, items.filter { it.type == "FamilyPosition" }, pollViewModel)
      PollProvider.bindStudyDegreeInput(requireContext(), pollView, items.filter { it.type == "StudyDegree" }, pollViewModel)
      PollProvider.bindOccupationsInput(requireContext(), pollView, items.filter { it.type == "Occupation" }, pollViewModel)
      PollProvider.bindMobilityMethodsInput(requireContext(), pollView, items.filter { it.type == "MobilityMethod" }, pollViewModel)
      PollProvider.bindDiseasesInput(requireContext(), pollView, items.filter { it.type == "Disease" }, pollViewModel)
      PollProvider.bindFederalSupportsInput(requireContext(), pollView, items.filter { it.type == "FederalSupport" }, pollViewModel)
      PollProvider.bindStateSupportsInput(requireContext(), pollView, items.filter { it.type == "StateSupport" }, pollViewModel)
      PollProvider.bindMunicipalSupportsInput(requireContext(), pollView, items.filter { it.type == "MunicipalSupport" }, pollViewModel)
      PollProvider.bindHobbiesInput(requireContext(), pollView, items.filter { it.type == "Hobby" }, pollViewModel)
      PollProvider.bindReligionsInput(requireContext(), pollView, items.filter { it.type == "Religion" }, pollViewModel)
      PollProvider.bindSportsInput(requireContext(), pollView, items.filter { it.type == "Sport" }, pollViewModel)
      PollProvider.bindSoccerTeamsInput(requireContext(), pollView, items.filter { it.type == "SoccerTeam" }, pollViewModel)
      PollProvider.bindPetTypesInput(requireContext(), pollView, items.filter { it.type == "PetType" }, pollViewModel)
      PollProvider.bindGovernmentInvitationActivityOrThemesInput(requireContext(), pollView, items.filter { it.type == "GovernmentInvitationActivitiesOrTheme" }, pollViewModel)
      PollProvider.bindGovernmentTaskActivityOrThemesInput(requireContext(), pollView, items.filter { it.type == "GovernmentTaskActivitiesOrTheme" }, pollViewModel)
      PollProvider.bindPollutionCauseInput(requireContext(), pollView, items.filter { it.type == "PollutionCause" }, pollViewModel)
    })
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val view: View = inflater.inflate(R.layout.fragment_new_poll_form, container, false)
    val sendButton = view.findViewById<Button>(R.id.btnEnviar)
    val btnIneScan = view.findViewById<Button>(R.id.btnIneScan)
    pollView = view
    // TODO: Implement date picker for BirthDate
    // TODO: Show selected options on multiple options dialog
    // TODO: Fix close button visibility on multiple options dialog, options list is pushing button outside of visible area at the bottom
    PollProvider.bindElectorKeyInput(view, pollViewModel)
    PollProvider.bindCurpInput(view, pollViewModel)
    PollProvider.bindNameInput(view, pollViewModel)
    PollProvider.bindSurNameInput(view, pollViewModel)
    PollProvider.bindSecondSurNameInput(view, pollViewModel)
    PollProvider.bindBirthDateInput(view, pollViewModel)
    PollProvider.bindBirthPlaceInput(view, pollViewModel)
    PollProvider.bindIneExpirationYearInput(view, pollViewModel)
    PollProvider.bindZipCodeInput(view, pollViewModel, optionsViewModel)
    PollProvider.bindGenderInput(view, pollViewModel)
    PollProvider.bindStreetInput(view, pollViewModel)
    PollProvider.bindExteriorNumberInput(view, pollViewModel)
    PollProvider.bindInteriorNumberInput(view, pollViewModel)
    PollProvider.bindIneLivingAddressInput(view, pollViewModel)
    PollProvider.bindOtherFamilyPositionInput(view, pollViewModel)
    PollProvider.bindCellPhoneNumberInput(view, pollViewModel)
    PollProvider.bindPhoneNumberInput(view, pollViewModel)
    PollProvider.bindFamilyIntegrantsNumberInput(view, pollViewModel)
    PollProvider.bindHouseHasInternetInput(view, pollViewModel)
    PollProvider.bindOtherOccupationInput(view, pollViewModel)
    PollProvider.bindOtherMobilityMethodInput(view, pollViewModel)
    PollProvider.bindOtherDiseasesInput(view, pollViewModel)
    PollProvider.bindOtherFederalSupportInput(view, pollViewModel)
    PollProvider.bindOtherStateSupportInput(view, pollViewModel)
    PollProvider.bindOtherMunicipalSupportInput(view, pollViewModel)
    PollProvider.bindOtherHobbiesInput(view, pollViewModel)
    PollProvider.bindOtherReligionInput(view, pollViewModel)
    PollProvider.bindOtherSportInput(view, pollViewModel)
    PollProvider.bindLikesPetsInput(view, pollViewModel)
    PollProvider.bindHasPetsInput(view, pollViewModel)
    PollProvider.bindOtherPetTypeInput(view, pollViewModel)
    PollProvider.bindOtherGovernmentInvitationActivityOrThemesInput(view, pollViewModel)
    PollProvider.bindPrincipalSectorProblematicInput(view, pollViewModel)
    PollProvider.bindFutureFiveYearsInput(view, pollViewModel)
    PollProvider.bindCommentsInput(view, pollViewModel)
    btnIneScan.setOnClickListener {
      initScanner()
    }
    sendButton.setOnClickListener {
      PollProvider.sendPoll(pollViewModel)
    }
    return view
  }

  private fun initScanner() {
    Log.e("INIT SCAN", "INIT")
    val options = ScanOptions()

    options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
    options.setPrompt("Coloca la credencial al centro de la camara")
    options.setBeepEnabled(true)
    barcodeLauncher.launch(options)
  }
}