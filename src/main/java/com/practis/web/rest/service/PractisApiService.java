package com.practis.web.rest.service;

import static com.practis.web.rest.configuration.PractisClientConfiguration.practisApiClient;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import com.practis.dto.NewAdminInput;
import com.practis.web.rest.dto.RestAdminRequest;
import com.practis.web.rest.dto.RestAdminResponse;
import com.practis.web.rest.dto.RestCompanyResponse;
import com.practis.web.rest.dto.RestLabelResponse;
import com.practis.web.rest.dto.RestLoginRequest;
import com.practis.web.rest.dto.RestSearchRequest;
import com.practis.web.rest.dto.user.SetCompanyRequest;
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
    final var request = RestSearchRequest.builder()
        .searchTerm(name)
        .build();
    return practisApiClient().searchLabel(request).getItems().stream().findFirst();
  }
}
