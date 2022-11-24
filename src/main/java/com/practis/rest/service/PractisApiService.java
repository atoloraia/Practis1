package com.practis.rest.service;

import static com.practis.rest.configuration.PractisApiClientConfiguration.practisApiClient;
import static com.practis.rest.configuration.PractisApiV2ClientConfiguration.practisApiClientV2;
import static com.practis.rest.mapper.ChallengeMapper.toRestCreateChallenge;
import static com.practis.rest.mapper.PractisSetMapper.toRestCreatePractisSet;
import static com.practis.rest.mapper.ScenarioMapper.toRestCreateScenario;
import static com.practis.utils.StringUtils.phone;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import com.practis.dto.NewAdminInput;
import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewCompanyInput;
import com.practis.dto.NewLabelInput;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewScenarioInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.RestSearchRequest;
import com.practis.rest.dto.admin.RestAdminRequest;
import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.rest.dto.admin.RestCompanyRequest;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.rest.dto.company.RestAssignLabelToTeamRequest;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestDeleteDraftUserRequest;
import com.practis.rest.dto.company.RestEnrollUnEnrollRequest;
import com.practis.rest.dto.company.RestRevokeRequest;
import com.practis.rest.dto.company.RestSearchLabelResponse;
import com.practis.rest.dto.company.RestStagingResponse;
import com.practis.rest.dto.company.RestTeamAddMembersRequest;
import com.practis.rest.dto.company.RestTeamCreateRequest;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.rest.dto.company.RestUserResponse;
import com.practis.rest.dto.company.library.RestAssignPractisSetRequest;
import com.practis.rest.dto.company.library.RestAssignPractisSetRequest.RestPractisSetEnrollmentRequest;
import com.practis.rest.dto.company.library.RestChallengeArchiveRequest;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestCreateChallenge;
import com.practis.rest.dto.company.library.RestCreateChallenge.Challenge;
import com.practis.rest.dto.company.library.RestCreateChallenge.Line;
import com.practis.rest.dto.company.library.RestCreateLabelRequest;
import com.practis.rest.dto.company.library.RestCreateScenario.Scenario;
import com.practis.rest.dto.company.library.RestPractisSetArchiveRequest;
import com.practis.rest.dto.company.library.RestPractisSetResponse;
import com.practis.rest.dto.company.library.RestScenarioArchiveRequest;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.rest.dto.user.InviteUserRequest;
import com.practis.rest.dto.user.RestLoginRequest;
import com.practis.rest.dto.user.SetCompanyRequest;
import com.practis.rest.dto.user.SignUpRequest;
import com.practis.rest.dto.user.SignUpUserResponseWrapper;
import com.practis.utils.FileUtils;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.SneakyThrows;

public class PractisApiService {

  private static String TOKEN;

  /**
   * Fetch auth token. Return cached if already fetched.
   */
  public static String getToken() {
    if (isNull(TOKEN)) {
      final var request = RestLoginRequest.builder()
          .login(webCredentialsConfig().getLogin())
          .password(webCredentialsConfig().getPassword())
          .build();

      TOKEN = practisApiClientV2().login(request).getToken();
    }
    return TOKEN;
  }

  public static void resetToken() {
    TOKEN = null;
  }

  /**
   * Set admin company.
   */
  public static RestAdminResponse setAdminCompany(final Integer userId) {
    final var request = SetCompanyRequest.builder()
        .companyId(null)
        .build();
    return practisApiClient().updateUser(userId, request);
  }

  /**
   * Set campaign.
   */
  public static RestAdminResponse setCompany(final Integer userId, final String companyName) {
    return practisApi().findCompany(companyName)
        .map(company -> SetCompanyRequest.builder()
            .companyId(company.getId())
            .build())
        .map(request -> practisApiClient().updateUser(userId, request))
        .orElseThrow(() -> new RuntimeException(
            format("Can't set company %s as active", companyName)));
  }


  /**
   * Create new company through API.
   */
  public RestCompanyResponse createCompany(final NewCompanyInput input) {
    final var request = RestCompanyRequest.builder()
        .name(input.getName())
        .ownerEmail(input.getEmail())
        .ownerFirstName(input.getFirstName())
        .ownerLastName(input.getLastName())
        .build();

    return practisApiClient().createCompany(List.of(request)).get(0);
  }

  /**
   * Create new admin through API.
   */
  public RestAdminResponse createAdmin(final NewAdminInput input) {
    final var request = RestAdminRequest.builder()
        .email(input.getEmail())
        .password(input.getPassword())
        .firstName(input.getFirstName())
        .lastName(input.getLastName())
        .build();

    return practisApiClient().createAdmin(List.of(request)).get(0);
  }

  /**
   * Delete an admin through API.
   */
  public void deleteAdmin(final String adminEmail) {
    findPractisAdmin(adminEmail).ifPresent(admin -> practisApiClient().deleteUser(admin.getId()));
  }

  /**
   * Delete a user through API.
   */
  public void deleteUser(final String userEmail) {
    findUser(userEmail).ifPresent(user -> practisApiClient().deleteUser(user.getId()));
  }

  /**
   * Revoke a user through API.
   */
  public void revokeUser(final String userEmail) {
    findInvitation(userEmail).ifPresent(user -> practisApiClient()
        .revokeUser(RestRevokeRequest.builder()
            .invitationIds(List.of(user.getId()))
            .build()));
  }

  /**
   * Delete draft user through API.
   */
  public void deleteDraftUser(final String draftName) {
    findDraftUser(draftName).ifPresent(draft -> practisApiClient()
        .deleteDraftUser(RestDeleteDraftUserRequest.builder()
            .stagingIds(List.of(draft.getId()))
            .build()));
  }

  /**
   * Find first find by email.
   */
  public Optional<RestUserResponse> findAdmin(final String email) {
    final var request = RestSearchRequest.builder()
        .searchTerm(email)
        .orderBy(Map.of(
            "field", "firstName",
            "asc", true))
        .build();
    return practisApiClient().searchAdmin(request).getItems().stream().findFirst();
  }

  /**
   * Find first find by email.
   */
  public Optional<RestUserResponse> findUser(final String email) {
    final var company = findCompany(webApplicationConfig().getAutomationCompanyName())
        .orElseThrow();
    return practisApiClientV2().searchUser(email, company.getId()).getItems().stream().findFirst();
  }

  /**
   * Find first find by email.
   */
  public Optional<RestUserResponse> findInvitation(final String email) {
    final var request = RestSearchRequest.builder()
        .searchTerm(email)
        .orderBy(Map.of(
            "field", "firstName",
            "asc", true))
        .build();
    return practisApiClient().searchInvitation(request).getItems().stream().findFirst();
  }

  /**
   * Find first find by email.
   */
  public Optional<RestStagingResponse> findDraftUser(final String name) {
    final var request = RestSearchRequest.builder()
        .searchTerm(name)
        .orderBy(Map.of(
            "field", "usersCount",
            "asc", true))
        .build();
    return practisApiClient().searchDraftUser(request).getItems().stream().findFirst();
  }


  /**
   * Find first admin by email.
   */
  public Optional<RestAdminResponse> findPractisAdmin(final String email) {
    final var request = RestSearchRequest.builder()
        .searchTerm(email)
        .orderBy(Map.of(
            "field", "firstName",
            "asc", true))
        .build();
    return practisApiClient().searchPractisAdmin(request).getItems().stream().findFirst();
  }

  public void deleteCompany(final String name) {
    findCompany(name).ifPresent(company -> practisApiClient().deleteCompany(company.getId()));
  }

  /**
   * Find first admin by email.
   */
  public Optional<RestCompanyResponse> findCompany(final String name) {
    final var request = RestSearchRequest.builder()
        .searchTerm(name)
        .orderBy(Map.of(
            "field", "name",
            "asc", true))
        .build();
    return practisApiClient().searchCompany(request).getItems().stream().findFirst();
  }

  public void deleteLabel(final String name) {
    findLabel(name).ifPresent(label -> practisApiClient().deleteLabel(label.getId()));
  }

  /**
   * Find first label by name.
   */
  public Optional<RestSearchLabelResponse> findLabel(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchLabel(request).getItems().stream().findFirst();
  }

  /**
   * Create Label through API.
   */
  public RestCreateLabelResponse createLabel(final NewLabelInput input) {
    final var request = RestCreateLabelRequest.builder()
        .name(input.getName()).build();
    return practisApiClient().createLabel(request);
  }


  /**
   * Delete Practis Set.
   */
  public void deletePractisSet(final String name) {
    findPractisSet(name).ifPresent(practisSet -> {
      final var request = RestPractisSetArchiveRequest.builder()
          .practisSetIds(List.of(practisSet.getId())).build();
      practisApiClient().archivePractisSet(request);
      practisApiClient().deletePractisSet(request);
    });
  }

  /**
   * Create Practis Set.
   *
   * @return
   */
  public RestPractisSetResponse createPractisSet(final NewPractisSetInput input,
      List<RestChallengeResponse> challenges, List<RestScenarioResponse> scenario) {
    final var request = toRestCreatePractisSet(input, challenges, scenario);
    return practisApiClient().createPractisSet(request);
  }

  /**
   * Find first practis set by name.
   */
  public Optional<RestPractisSetResponse> findPractisSet(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchPractisSet(request).getItems().stream().findFirst();
  }

  /**
   * Find first scenario by name.
   */
  public Optional<RestScenarioResponse> findScenario(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchScenario(request).getItems().stream().findFirst();
  }

  /**
   * Create scenario.
   */
  public RestScenarioResponse createScenarioWithLines(final NewScenarioInput input) {
    return practisApiClientV2().createScenario(Scenario.builder()
        .title(input.getTitle())
        .description(input.getDescription())
        .build());
  }

  /**
   * Create scenario with lines.
   */
  @SneakyThrows
  public RestScenarioResponse createScenarioWithLines(final NewScenarioInput input,
      final String fileName) {
    final var audioFile = ofNullable(PractisApiService.class.getResource(fileName))
        .map(FileUtils::fromResource)
        .orElseThrow(() -> new RuntimeException(format("File '%s' not found", fileName)));
    final var lineAudio = practisApiClient().uploadLine(audioFile, "AUDIO", "Line");
    final var request = toRestCreateScenario(input, lineAudio);

    return practisApiClient().createScenarioWithLines(request);
  }

  /**
   * Delete scenario.
   */
  public void deleteScenario(final String name) {
    findScenario(name).ifPresent(scenario -> practisApiClient().archiveScenario(
        RestScenarioArchiveRequest.builder().scenarioIds(List.of(scenario.getId())).build()));
  }

  /**
   * Delete challenge.
   */
  public void deleteChallenge(final String name) {
    findChallenge(name).ifPresent(challenge -> practisApiClient().archiveChallenge(
        RestChallengeArchiveRequest.builder().challengeIds(List.of(challenge.getId())).build()));
  }

  /**
   * Create challenge.
   */
  public RestChallengeResponse createChallenge(final NewChallengeInput input) {
    final var request = RestCreateChallenge.builder()
        .challenge(Challenge.builder()
            .description(input.getDescription())
            .title(input.getTitle())
            .build())
        .lines(input.getCustomerLines().stream()
            .map(text -> Line.builder().text(text).build())
            .collect(toList()))
        .build();

    return practisApiClientV2().createChallenge(request);
  }

  /**
   * Create challenge with lines.
   */
  @SneakyThrows
  public RestChallengeResponse createChallengeWithLines(
      final NewChallengeInput input, final String fileName) {
    final var audioFile = ofNullable(PractisApiService.class.getResource(fileName))
        .map(FileUtils::fromResource)
        .orElseThrow(() -> new RuntimeException(format("File '%s' not found", fileName)));
    final var lineAudio = practisApiClient().uploadLine(audioFile, "AUDIO", "Line");
    final var request = toRestCreateChallenge(input, lineAudio);

    return practisApiClient().createChallengeWithLines(request);
  }

  /**
   * Find first challenge by name.
   */
  public Optional<RestChallengeResponse> findChallenge(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchChallenge(request).getItems().stream().findFirst();
  }

  /**
   * Create Team.
   */
  public NewTeamInput createTeam(final String name) {
    final var request = RestTeamCreateRequest.builder().name(name).build();
    return practisApiClientV2().createTeam(request);
  }

  /**
   * Assign Label to the Team.
   */
  public void assignLabelToTeam(final Integer teamId, List<Integer> labelIds) {
    final var request = labelIds.stream().map(labelId -> RestAssignLabelToTeamRequest.builder()
            .teamId(teamId)
            .labelId(labelId)
            .build())
        .collect(toList());
    practisApiClientV2().assignLabelToTeam(request);
  }

  /**
   * Add Members to the Team.
   */
  public void addMembersToTeam(final Integer teamId, List<Integer> usersIds) {
    final var request = usersIds.stream().map(userId -> RestTeamAddMembersRequest.builder()
            .teamId(teamId)
            .userId(userId)
            .build())
        .collect(toList());
    practisApiClientV2().addMembersToTeam(request);
  }

  /**
   * Delete Team.
   */
  public void deleteTeam(final String name) {
    findTeam(name).ifPresent(team -> practisApiClientV2().deleteTeam(List.of(team.getId())));
  }

  /**
   * Find Team by name.
   */
  public Optional<RestTeamResponse> findTeam(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchTeam(request).getItems().stream().findFirst();
  }

  /**
   * Invite Users.
   */
  public List<NewUserInput> inviteUsers(final List<NewUserInput> users) {
    final var request = users.stream()
        .map(user -> InviteUserRequest.builder()
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .companyId(user.getCompanyId())
            .roleId(user.getRoleId())
            .build())
        .collect(toList());
    return practisApiClientV2().inviteUsers(request)
        .stream()
        .map(user -> NewUserInput.builder()
            .id(user.getId())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .roleId(user.getRoleId())
            .companyId(user.getCompanyId())
            .invitationCode(user.getInvitationCode())
            .build())
        .collect(toList());
  }


  /**
   * Assign Practis Set to the team.
   */
  public void assignPractisSet(final Integer psID, final Integer usersId) {
    final var enroll = RestAssignPractisSetRequest.builder()
        .practisSets(List.of(RestPractisSetEnrollmentRequest.builder()
            .practisSetId(psID)
            .build()))
        .userId(List.of(usersId))
        .build();
    final var request = RestEnrollUnEnrollRequest.builder().enroll(enroll).build();
    practisApiClient().enroll(request);
  }


  /**
   * Sign Up User.
   */
  public List<NewUserInput> signupUsers(final List<NewUserInput> users) {
    final var invites = inviteUsers(users);
    return invites.stream()
        .map(invite -> practisApiClient().signUpUser(SignUpRequest.builder()
            .firstName(invite.getFirstName())
            .lastName(invite.getLastName())
            .email(invite.getEmail())
            .password(timestamp())
            .invitationCode(invite.getInvitationCode())
            .phoneNumber(phone())
            .build()))
        .map(SignUpUserResponseWrapper::getUser)
        .map(signUp -> NewUserInput.builder()
            .id(signUp.getId())
            .email(signUp.getEmail())
            .firstName(signUp.getFirstName())
            .lastName(signUp.getLastName())
            .companyId(signUp.getCompanyId())
            .roleId(signUp.getRoleId())
            .build())
        .collect(toList());
  }


  private RestSearchRequest getRestSearchRequest(final String searchTerm) {
    return RestSearchRequest.builder()
        .searchTerm(searchTerm)
        .build();
  }
}
