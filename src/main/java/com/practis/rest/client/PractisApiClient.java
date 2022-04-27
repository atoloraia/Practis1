package com.practis.rest.client;

import com.practis.rest.dto.RestSearchRequest;
import com.practis.rest.dto.RestSearchResponse;
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
import com.practis.rest.dto.user.RestLoginResponse;
import com.practis.rest.dto.user.SetCompanyRequest;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface PractisApiClient {

  @RequestLine("POST /api/auth/login")
  RestLoginResponse login(RestLoginRequest request);

  @RequestLine("POST /api/admin/users")
  List<RestAdminResponse> createAdmin(List<RestAdminRequest> request);

  @RequestLine("POST /api/admin/companies")
  List<RestCompanyResponse> createCompany(List<RestCompanyRequest> request);

  @RequestLine("DELETE /api/admin/users/{adminId}")
  void deleteAdmin(@Param("adminId") Integer adminId);

  @RequestLine("POST /api/admin/users/practis_admin/search")
  RestSearchResponse<RestAdminResponse> searchAdmin(RestSearchRequest adminId);

  @RequestLine("DELETE /api/admin/companies/{companyId}")
  void deleteCompany(@Param("companyId") Integer companyId);

  @RequestLine("POST /api/admin/companies/search")
  RestSearchResponse<RestCompanyResponse> searchCompany(RestSearchRequest searchRequest);

  @RequestLine("PUT /api/admin/users/{userId}?skipLog=true")
  RestAdminResponse updateUser(@Param("userId") Integer userId, SetCompanyRequest request);

  @RequestLine("POST /api/labels/search")
  RestSearchResponse<RestLabelResponse> searchLabel(RestSearchRequest searchRequest);

  @RequestLine("DELETE /api/labels/{labelId}")
  void deleteLabel(@Param("labelId") Integer labelId);

  @RequestLine("POST /api/practisSets/search")
  RestSearchResponse<RestPractisSet> searchPractisSet(RestSearchRequest searchRequest);

  @RequestLine("PUT /api/practisSets/archive")
  void archivePractisSet(RestPractisSetArchiveRequest request);

  @RequestLine("POST /api/scenarios/search")
  RestSearchResponse<RestScenario> searchScenario(RestSearchRequest searchRequest);

  @RequestLine("PUT /api/scenarios/archive")
  void archiveScenario(RestScenarioArchiveRequest request);

  @RequestLine("POST /api/challenges/search")
  RestSearchResponse<RestChallenge> searchChallenge(RestSearchRequest searchRequest);

  @RequestLine("PUT /api/challenges/archive")
  void archiveChallenge(RestChallengeArchiveRequest request);

  @RequestLine("POST /api/teams/search")
  RestSearchResponse<RestTeam> searchTeam(RestSearchRequest searchRequest);

  @RequestLine("DELETE /api/teams")
  void deleteTeam(RestTeamDeleteRequest request);

}
