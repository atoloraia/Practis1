package com.practis.web.rest.service;

import static com.practis.web.rest.configuration.PractisClientConfiguration.practisApiClient;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static java.util.Objects.isNull;

import com.practis.dto.NewAdminInput;
import com.practis.web.rest.dto.RestAdminRequest;
import com.practis.web.rest.dto.RestAdminResponse;
import com.practis.web.rest.dto.RestLoginRequest;
import com.practis.web.rest.dto.RestSearchRequest;
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
}
