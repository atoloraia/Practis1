package com.practis.rest.service;

import static com.practis.rest.configuration.PractisClientConfiguration.practisApiClient;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import com.practis.dto.NewAdminInput;
import com.practis.dto.NewCompanyInput;
import com.practis.rest.dto.RestSearchRequest;
import com.practis.rest.dto.admin.RestAdminRequest;
import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.rest.dto.admin.RestCompanyRequest;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.rest.dto.company.RestLabelResponse;
import com.practis.rest.dto.company.RestTeam;
import com.practis.rest.dto.company.RestTeamDeleteRequest;
import com.practis.rest.dto.company.library.RestChallenge;
import com.practis.rest.dto.company.library.RestChallengeArchiveRequest;
import com.practis.rest.dto.company.library.RestPractisSet;
import com.practis.rest.dto.company.library.RestPractisSetArchiveRequest;
import com.practis.rest.dto.company.library.RestScenario;
import com.practis.rest.dto.company.library.RestScenarioArchiveRequest;
import com.practis.rest.dto.user.RestLoginRequest;
import com.practis.rest.dto.user.SetCompanyRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

  public void deleteAdmin(final String adminEmail) {
    findAdmin(adminEmail).ifPresent(admin -> practisApiClient().deleteAdmin(admin.getId()));
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
  public Optional<RestLabelResponse> findLabel(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchLabel(request).getItems().stream().findFirst();
  }

  public void deletePractisSet(final String name) {
    findPractisSet(name).ifPresent(practisSet -> practisApiClient().archivePractisSet(
        RestPractisSetArchiveRequest.builder().practisSetIds(List.of(practisSet.getId())).build()));
  }

  /**
   * Find first practis set by name.
   */
  public Optional<RestPractisSet> findPractisSet(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchPractisSet(request).getItems().stream().findFirst();
  }

  public void deleteScenario(final String name) {
    findScenario(name).ifPresent(scenario -> practisApiClient().archiveScenario(
        RestScenarioArchiveRequest.builder().scenarioIds(List.of(scenario.getId())).build()));
  }

  /**
   * Find first scenario by name.
   */
  public Optional<RestScenario> findScenario(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchScenario(request).getItems().stream().findFirst();
  }

  public void deleteChallenge(final String name) {
    findChallenge(name).ifPresent(challenge -> practisApiClient().archiveChallenge(
        RestChallengeArchiveRequest.builder().challengeIds(List.of(challenge.getId())).build()));
  }

  /**
   * Find first challenge by name.
   */
  public Optional<RestChallenge> findChallenge(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchChallenge(request).getItems().stream().findFirst();
  }

  public void deleteTeam(final String name) {
    findTeam(name).ifPresent(team -> practisApiClient().deleteTeam(
        RestTeamDeleteRequest.builder().teamIds(List.of(team.getId())).build()));
  }

  /**
   * Find first practis set by name.
   */
  public Optional<RestTeam> findTeam(final String name) {
    final var request = getRestSearchRequest(name);
    return practisApiClient().searchTeam(request).getItems().stream().findFirst();
  }

  private RestSearchRequest getRestSearchRequest(final String searchTerm) {
    return RestSearchRequest.builder()
        .searchTerm(searchTerm)
        .build();
  }
}
