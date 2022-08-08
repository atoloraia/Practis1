package com.practis.rest.service;

import static com.practis.rest.configuration.PractisClientConfiguration.practisApiClient;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import com.practis.dto.NewAdminInput;
import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewCompanyInput;
import com.practis.dto.NewLabelInput;
import com.practis.dto.NewScenarioInput;
import com.practis.rest.dto.RestSearchRequest;
import com.practis.rest.dto.admin.RestAdminRequest;
import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.rest.dto.admin.RestCompanyRequest;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestRevokeRequest;
import com.practis.rest.dto.company.RestSearchLabelResponse;
import com.practis.rest.dto.company.RestTeamCreateRequest;
import com.practis.rest.dto.company.RestTeamDeleteRequest;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.rest.dto.company.RestUserResponse;
import com.practis.rest.dto.company.library.RestChallenge;
import com.practis.rest.dto.company.library.RestChallengeArchiveRequest;
import com.practis.rest.dto.company.library.RestCreateChallenge;
import com.practis.rest.dto.company.library.RestCreateChallenge.Challenge;
import com.practis.rest.dto.company.library.RestCreateChallenge.Line;
import com.practis.rest.dto.company.library.RestCreateLabelRequest;
import com.practis.rest.dto.company.library.RestCreateScenario.Scenario;
import com.practis.rest.dto.company.library.RestPractisSetArchiveRequest;
import com.practis.rest.dto.company.library.RestPractisSetResponse;
import com.practis.rest.dto.company.library.RestScenarioArchiveRequest;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.rest.dto.user.RestLoginRequest;
import com.practis.rest.dto.user.SetCompanyRequest;
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
          .email(webCredentialsConfig().getLogin())
          .password(webCredentialsConfig().getPassword())
          .build();

      TOKEN = practisApiClient().login(request).getToken();
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
    findAdmin(adminEmail).ifPresent(admin -> practisApiClient().deleteUser(admin.getId()));
  }

  /**
   * Delete a user through API.
   */
  public void deleteUser(final String userEmail) {
    findUser(userEmail).ifPresent(user -> practisApiClient().deleteUser(user.getId()));
  }

  /**
   * Delete a user through API.
   */
  public void revokeUser(final String userEmail) {
    findUser(userEmail).ifPresent(user -> practisApiClient()
        .revokeUser(RestRevokeRequest.builder()
            .invitationIds(List.of(user.getId()))
            .build()));
  }

  /**
   * Find first find by email.
   */
  public Optional<RestUserResponse> findUser(final String email) {
    final var request = RestSearchRequest.builder()
        .searchTerm(email)
        .orderBy(Map.of(
            "field", "firstName",
            "asc", true))
        .build();
    return practisApiClient().searchUser(request).getItems().stream().findFirst();
  }


  /**
   * Find first admin by email.
   */
  public Optional<RestAdminResponse> findAdmin(final String email) {
    final var request = RestSearchRequest.builder()
        .searchTerm(email)
        .orderBy(Map.of(
            "field", "firstName",
            "asc", true))
        .build();
    return practisApiClient().searchAdmin(request).getItems().stream().findFirst();
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
    findPractisSet(name).ifPresent(practisSet -> practisApiClient().archivePractisSet(
        RestPractisSetArchiveRequest.builder().practisSetIds(List.of(practisSet.getId())).build()));
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
  @SneakyThrows
  public RestScenarioResponse createScenario(final NewScenarioInput input) {
    return practisApiClient().createScenario(Scenario.builder()
        .title(input.getTitle())
        .description(input.getDescription())
        .build());
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
  public RestChallenge createChallenge(final NewChallengeInput input) {
    final var request = RestCreateChallenge.builder()
        .challenge(Challenge.builder()
            .description(input.getDescription())
            .title(input.getTitle())
            .build())
        .lines(input.getCustomerLines().stream()
            .map(text -> Line.builder().text(text).build())
            .collect(toList()))
        .build();

    return practisApiClient().createChallenge(request);
  }

  /**
   * Find first challenge by name.
   */
  public Optional<RestChallenge> findChallenge(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchChallenge(request).getItems().stream().findFirst();
  }

  /**
   * Create Team.
   */
  public RestTeamResponse createTeam(final String name) {
    final var request = RestTeamCreateRequest.builder().name(name).build();
    return practisApiClient().createTeam(request);
  }

  /**
   * Delete Team.
   */
  public void deleteTeam(final String name) {
    findTeam(name).ifPresent(team -> practisApiClient().deleteTeam(
        RestTeamDeleteRequest.builder().teamIds(List.of(team.getId())).build()));
  }

  /**
   * Find Team by name.
   */
  public Optional<RestTeamResponse> findTeam(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchTeam(request).getItems().stream().findFirst();
  }

  private RestSearchRequest getRestSearchRequest(final String searchTerm) {
    return RestSearchRequest.builder()
        .searchTerm(searchTerm)
        .build();
  }
}
