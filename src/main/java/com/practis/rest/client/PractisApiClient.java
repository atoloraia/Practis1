package com.practis.rest.client;

import com.practis.rest.dto.RestCollection;
import com.practis.rest.dto.RestSearchRequest;
import com.practis.rest.dto.admin.RestAdminRequest;
import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.rest.dto.admin.RestCompanyRequest;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestDeleteDraftUserRequest;
import com.practis.rest.dto.company.RestRevokeRequest;
import com.practis.rest.dto.company.RestSearchLabelResponse;
import com.practis.rest.dto.company.RestStagingResponse;
import com.practis.rest.dto.company.RestTeamCreateRequest;
import com.practis.rest.dto.company.RestTeamDeleteRequest;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.rest.dto.company.RestUserResponse;
import com.practis.rest.dto.company.library.RestChallenge;
import com.practis.rest.dto.company.library.RestChallengeArchiveRequest;
import com.practis.rest.dto.company.library.RestCreateChallenge;
import com.practis.rest.dto.company.library.RestCreateLabelRequest;
import com.practis.rest.dto.company.library.RestCreateScenario.Scenario;
import com.practis.rest.dto.company.library.RestPractisSetArchiveRequest;
import com.practis.rest.dto.company.library.RestPractisSetResponse;
import com.practis.rest.dto.company.library.RestScenarioArchiveRequest;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.rest.dto.user.InviteUserRequest;
import com.practis.rest.dto.user.InviteUserResponse;
import com.practis.rest.dto.user.RestLoginRequest;
import com.practis.rest.dto.user.RestLoginResponse;
import com.practis.rest.dto.user.SetCompanyRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import java.util.Map;

public interface PractisApiClient {

  @RequestLine("POST /api/auth/login")
  @Headers("Content-Type: application/json")
  RestLoginResponse login(RestLoginRequest request);

  @RequestLine("POST /api/admin/users")
  @Headers("Content-Type: application/json")
  List<RestAdminResponse> createAdmin(List<RestAdminRequest> request);

  @RequestLine("POST /api/admin/companies")
  @Headers("Content-Type: application/json")
  List<RestCompanyResponse> createCompany(List<RestCompanyRequest> request);

  @RequestLine("DELETE /api/admin/users/{userId}")
  @Headers("Content-Type: application/json")
  void deleteUser(@Param("userId") Integer userId);

  @RequestLine("DELETE /api/invitations/revoke/")
  @Headers("Content-Type: application/json")
  void revokeUser(RestRevokeRequest request);

  @RequestLine("DELETE /api/staging/")
  @Headers("Content-Type: application/json")
  void deleteDraftUser(RestDeleteDraftUserRequest request);

  @RequestLine("POST /api/admin/users/practis_admin/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestAdminResponse> searchAdmin(RestSearchRequest adminId);

  @RequestLine("POST /api/staging/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestStagingResponse> searchDraftUser(RestSearchRequest stagingId);

  @RequestLine("POST /api/admin/users/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestUserResponse> searchUser(RestSearchRequest adminId);

  @RequestLine("DELETE /api/admin/companies/{companyId}")
  @Headers("Content-Type: application/json")
  void deleteCompany(@Param("companyId") Integer companyId);

  @RequestLine("POST /api/admin/companies/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestCompanyResponse> searchCompany(RestSearchRequest searchRequest);

  @RequestLine("PUT /api/admin/users/{userId}?skipLog=true")
  @Headers("Content-Type: application/json")
  RestAdminResponse updateUser(@Param("userId") Integer userId, SetCompanyRequest request);

  @RequestLine("POST /api/labels/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestSearchLabelResponse> searchLabel(RestSearchRequest searchRequest);

  @RequestLine("POST /api/labels")
  @Headers("Content-Type: application/json")
  RestCreateLabelResponse createLabel(RestCreateLabelRequest createRequest);

  @RequestLine("DELETE /api/labels/{labelId}")
  @Headers("Content-Type: application/json")
  void deleteLabel(@Param("labelId") Integer labelId);

  @RequestLine("POST /api/practisSets/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestPractisSetResponse> searchPractisSet(RestSearchRequest searchRequest);

  @RequestLine("PUT /api/practisSets/archive")
  @Headers("Content-Type: application/json")
  void archivePractisSet(RestPractisSetArchiveRequest request);

  @RequestLine("POST /api/scenarios/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestScenarioResponse> searchScenario(RestSearchRequest searchRequest);

  @RequestLine("POST /api/scenarios")
  @Headers("Content-Type: application/json")
  RestScenarioResponse createScenario(Scenario request);

  @RequestLine("PUT /api/scenarios/archive")
  @Headers("Content-Type: application/json")
  void archiveScenario(RestScenarioArchiveRequest request);

  @RequestLine("POST /api/challenges")
  @Headers("Content-Type: application/json")
  RestChallenge createChallenge(RestCreateChallenge request);

  @RequestLine("POST /api/challenges/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestChallenge> searchChallenge(RestSearchRequest searchRequest);

  @RequestLine("PUT /api/challenges/archive")
  @Headers("Content-Type: application/json")
  void archiveChallenge(RestChallengeArchiveRequest request);

  @RequestLine("POST /api/teams/search")
  @Headers("Content-Type: application/json")
  RestCollection<RestTeamResponse> searchTeam(RestSearchRequest searchRequest);

  @RequestLine("DELETE /api/teams")
  @Headers("Content-Type: application/json")
  void deleteTeam(RestTeamDeleteRequest request);

  @RequestLine("POST /api/teams")
  @Headers("Content-Type: application/json")
  RestTeamResponse createTeam(RestTeamCreateRequest request);

  @RequestLine("POST /api/invitations")
  @Headers("Content-Type: application/json")
  Map<String, InviteUserResponse> inviteUsers(RestCollection<InviteUserRequest> request);

}
