package com.practis.rest.client;

import com.practis.rest.dto.RestCollection;
import com.practis.rest.dto.RestSearchRequest;
import com.practis.rest.dto.admin.RestAdminRequest;
import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.rest.dto.admin.RestCompanyRequest;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.rest.dto.company.RestCreateDraftUserRequest;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestDeleteDraftUserRequest;
import com.practis.rest.dto.company.RestRevokeRequest;
import com.practis.rest.dto.company.RestSearchLabelResponse;
import com.practis.rest.dto.company.RestStagingResponse;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.rest.dto.company.RestUserResponse;
import com.practis.rest.dto.company.audio.SaveFileResponse;
import com.practis.rest.dto.company.library.RestChallengeArchiveRequest;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestCreateChallenge;
import com.practis.rest.dto.company.library.RestCreateLabelRequest;
import com.practis.rest.dto.company.library.RestCreateScenario;
import com.practis.rest.dto.company.library.RestPractisSetArchiveRequest;
import com.practis.rest.dto.company.library.RestPractisSetRequest;
import com.practis.rest.dto.company.library.RestPractisSetResponse;
import com.practis.rest.dto.company.library.RestScenarioArchiveRequest;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.rest.dto.user.SetCompanyRequest;
import com.practis.rest.dto.user.SignUpRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.io.File;
import java.util.List;

public interface PractisApiClient {
    // v1
    @RequestLine("POST /api/admin/users")
    @Headers("Content-Type: application/json")
    List<RestAdminResponse> createAdmin(List<RestAdminRequest> request);
    // v1
    @RequestLine("POST /api/admin/companies")
    @Headers("Content-Type: application/json")
    List<RestCompanyResponse> createCompany(List<RestCompanyRequest> request);

    // v2
    @RequestLine("DELETE /api/admin/users/{userId}")
    @Headers("Content-Type: application/json")
    void deleteUser(@Param("userId") Integer userId);
    // v2
    @RequestLine("DELETE /api/invitations/revoke/")
    @Headers("Content-Type: application/json")
    void revokeUser(RestRevokeRequest request);

    // v1
    @RequestLine("DELETE /api/staging/")
    @Headers("Content-Type: application/json")
    void deleteDraftUser(RestDeleteDraftUserRequest request);

    // v1
    @RequestLine("POST /api/staging/")
    @Headers("Content-Type: application/json")
    RestStagingResponse createDraftUser(RestCreateDraftUserRequest request);
    // v2
    @RequestLine("POST /api/admin/users/practis_admin/search/")
    @Headers("Content-Type: application/json")
    RestCollection<RestAdminResponse> searchPractisAdmin(RestSearchRequest adminId);
    // v1
    @RequestLine("POST /api/staging/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestStagingResponse> searchDraftUser(RestSearchRequest stagingId);

    @RequestLine("POST /api/admin/users/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestUserResponse> searchAdmin(RestSearchRequest adminId);
    // v2
    @RequestLine("POST /api/users/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestUserResponse> searchUser(RestSearchRequest userId);
    // v2
    @RequestLine("PUT /api/admin/users/{userId}?skipLog=true")
    @Headers("Content-Type: application/json")
    RestAdminResponse updateUser(@Param("userId") Integer userId, SetCompanyRequest request);
    // v1
    @RequestLine("POST /api/labels/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestSearchLabelResponse> searchLabel(RestSearchRequest searchRequest);
    // v1
    @RequestLine("POST /api/labels/")
    @Headers("Content-Type: application/json")
    RestCreateLabelResponse createLabel(RestCreateLabelRequest createRequest);
    // v1
    @RequestLine("DELETE /api/labels/{labelId}")
    @Headers("Content-Type: application/json")
    void deleteLabel(@Param("labelId") Integer labelId);
    // v2
    @RequestLine("POST /api/practisSets/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestPractisSetResponse> searchPractisSet(RestSearchRequest searchRequest);
    // v2
    @RequestLine("PUT /api/practisSets/archive")
    @Headers("Content-Type: application/json")
    void archivePractisSet(RestPractisSetArchiveRequest request);
    // v2
    @RequestLine("POST /api/practisSets")
    @Headers("Content-Type: application/json")
    RestPractisSetResponse createPractisSet(RestPractisSetRequest request);
    // v2
    @RequestLine("DELETE /api/practisSets")
    @Headers("Content-Type: application/json")
    void deletePractisSet(RestPractisSetArchiveRequest request);
    // v2
    @RequestLine("POST /api/scenarios/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestScenarioResponse> searchScenario(RestSearchRequest searchRequest);
    // v2
    @RequestLine("POST /api/scenarios/save")
    @Headers("Content-Type: application/json")
    RestScenarioResponse createScenarioWithLines(RestCreateScenario request);
    // v2
    @RequestLine("PUT /api/scenarios/archive")
    @Headers("Content-Type: application/json")
    void archiveScenario(RestScenarioArchiveRequest request);
    // v2
    @RequestLine("POST /api/challenges")
    @Headers("Content-Type: application/json")
    RestChallengeResponse createChallengeWithLines(RestCreateChallenge request);
    // v2
    @RequestLine("PUT /api/challenges/archive")
    @Headers("Content-Type: application/json")
    void archiveChallenge(RestChallengeArchiveRequest request);
    // v2
    @RequestLine("POST /api/teams/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestTeamResponse> searchTeam(RestSearchRequest searchRequest);

    @RequestLine("POST /api/files")
    @Headers("Content-Type: multipart/form-data")
    SaveFileResponse uploadLine(
            @Param("file") File file,
            @Param("type") String type,
            @Param("associatedEntityType") String associatedEntityType);
    // v2
    @RequestLine("GET /api/invitations/{code}/")
    @Headers("Content-Type: application/json")
    SignUpRequest getInvite(@Param("code") String invitationCode);
}
