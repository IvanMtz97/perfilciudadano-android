package com.example.perfilciudadano.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.perfilciudadano.R
import com.example.perfilciudadano.models.Poll
import com.example.perfilciudadano.providers.PollProvider
import com.example.perfilciudadano.viewmodel.*

class NewPollFormFragment : Fragment() {
  private lateinit var pollView: View
  private val pollViewModel: PollViewModel by activityViewModels()
  private val sectionsViewModel: SectionsViewModel by activityViewModels()
  private val zipCodesViewModel: ZipCodesViewModel by activityViewModels()
  private val coloniesViewModel: ColoniesViewModel by activityViewModels()
  private val maritalStatusesViewModel: MaritalStatusesViewModel by activityViewModels()
  private val familyPositionsViewModel: FamilyPositionsViewModel by activityViewModels()
  private val studyDegreesViewModel: StudyDegreesViewModel by activityViewModels()
  private val occupationsViewModel: OccupationsViewModel by activityViewModels()
  private val mobilityMethodsViewModel: MobilityMethodsViewModel by activityViewModels()
  private val diseasesViewModel: DiseasesViewModel by activityViewModels()
  private val federalSupportsViewModel: FederalSupportsViewModel by activityViewModels()
  private val stateSupportsViewModel: StateSupportsViewModel by activityViewModels()
  private val municipalSupportsViewModel: MunicipalSupportsViewModel by activityViewModels()
  private val hobbiesViewModel: HobbiesViewModel by activityViewModels()
  private val religionsViewModel: ReligionsViewModel by activityViewModels()
  private val sportsViewModel: SportsViewModel by activityViewModels()
  private val soccerTeamsViewModel: SoccerTeamsViewModel by activityViewModels()
  private val petTypesViewModel: PetTypesViewModel by activityViewModels()
  private val governmentInvitationActivityOrThemesViewModel: GovernmentInvitationActivityOrThemesViewModel by activityViewModels()
  private val governmentTaskActivityOrThemesViewModel: GovernmentTaskActivityOrThemesViewModel by activityViewModels()
  private val pollutionCausesViewModel: PollutionCausesViewModel by activityViewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    zipCodesViewModel.zipCodes.observe(this, Observer { item ->
      PollProvider.bindZipCodeInput(requireContext(), pollView, item, pollViewModel, zipCodesViewModel)
    })
    sectionsViewModel.sections.observe(this, Observer { item ->
      PollProvider.bindSectionInput(requireContext(), pollView, item, pollViewModel, sectionsViewModel)
    })
    coloniesViewModel.colonies.observe(this, Observer { item ->
      PollProvider.bindColonyInput(requireContext(), pollView, item, pollViewModel, coloniesViewModel)
    })
    maritalStatusesViewModel.maritalStatuses.observe(this, Observer { item ->
      PollProvider.bindMaritalStatusInput(requireContext(), pollView, item, pollViewModel, maritalStatusesViewModel)
    })
    familyPositionsViewModel.familyPositions.observe(this, Observer { item ->
      PollProvider.bindFamilyPositionsInput(requireContext(), pollView, item, pollViewModel, familyPositionsViewModel)
    })
    studyDegreesViewModel.studyDegrees.observe(this, Observer { item ->
      PollProvider.bindStudyDegreeInput(requireContext(), pollView, item, pollViewModel, studyDegreesViewModel)
    })
    occupationsViewModel.occupations.observe(this, Observer { item ->
      PollProvider.bindOccupationsInput(requireContext(), pollView, item, pollViewModel, occupationsViewModel)
    })
    mobilityMethodsViewModel.mobilityMethods.observe(this, Observer { item ->
      PollProvider.bindMobilityMethodsInput(requireContext(), pollView, item, pollViewModel, mobilityMethodsViewModel)
    })
    diseasesViewModel.diseases.observe(this, Observer { items ->
      PollProvider.bindDiseasesInput(requireContext(), pollView, items, pollViewModel, diseasesViewModel)
    })
    federalSupportsViewModel.federalSupports.observe(this, Observer { items ->
      PollProvider.bindFederalSupportsInput(requireContext(), pollView, items, pollViewModel, federalSupportsViewModel)
    })
    stateSupportsViewModel.stateSupports.observe(this, Observer { items ->
      PollProvider.bindStateSupportsInput(requireContext(), pollView, items, pollViewModel, stateSupportsViewModel)
    })
    municipalSupportsViewModel.municipalSupports.observe(this, Observer { items ->
      PollProvider.bindMunicipalSupportsInput(requireContext(), pollView, items, pollViewModel, municipalSupportsViewModel)
    })
    hobbiesViewModel.hobbies.observe(this, Observer { items ->
      PollProvider.bindHobbiesInput(requireContext(), pollView, items, pollViewModel, hobbiesViewModel)
    })
    religionsViewModel.religions.observe(this, Observer { items ->
      PollProvider.bindReligionsInput(requireContext(), pollView, items, pollViewModel, religionsViewModel)
    })
    sportsViewModel.sports.observe(this, Observer { items ->
      PollProvider.bindSportsInput(requireContext(), pollView, items, pollViewModel, sportsViewModel)
    })
    soccerTeamsViewModel.soccerTeams.observe(this, Observer { item ->
      PollProvider.bindSoccerTeamsInput(requireContext(), pollView, item, pollViewModel, soccerTeamsViewModel)
    })
    petTypesViewModel.petTypes.observe(this, Observer { item ->
      PollProvider.bindPetTypesInput(requireContext(), pollView, item, pollViewModel, petTypesViewModel)
    })
    governmentInvitationActivityOrThemesViewModel.options.observe(this, Observer { items ->
      PollProvider.bindGovernmentInvitationActivityOrThemesInput(requireContext(), pollView, items, pollViewModel, governmentInvitationActivityOrThemesViewModel)
    })
    governmentTaskActivityOrThemesViewModel.options.observe(this, Observer { items ->
      PollProvider.bindGovernmentTaskActivityOrThemesInput(requireContext(), pollView, items, pollViewModel, governmentTaskActivityOrThemesViewModel)
    })
    pollutionCausesViewModel.options.observe(this, Observer { items ->
      PollProvider.bindPollutionCauseInput(requireContext(), pollView, items, pollViewModel, pollutionCausesViewModel)
    })
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view: View = inflater.inflate(R.layout.fragment_new_poll_form, container, false)
    val sendButton = view.findViewById<Button>(R.id.btnEnviar)
    pollView = view
    // TODO: Implement elector key validation with regex or length
    // TODO: Implement CURP validation with regex
    // TODO: Implement date picker for BirthDate
    PollProvider.bindElectorKeyInput(view, pollViewModel)
    PollProvider.bindCurpInput(view, pollViewModel)
    PollProvider.bindNameInput(view, pollViewModel)
    PollProvider.bindSurNameInput(view, pollViewModel)
    PollProvider.bindSecondSurNameInput(view, pollViewModel)
    PollProvider.bindBirthDateInput(view, pollViewModel)
    PollProvider.bindBirthPlaceInput(view, pollViewModel)
    PollProvider.bindIneExpirationYearInput(view, pollViewModel)
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
    sendButton.setOnClickListener {
      PollProvider.sendPoll(pollViewModel)
    }
    return view
  }
}