package com.practis.rest.client;

import com.practis.dto.NewTeamInput;
import com.practis.rest.dto.RestCollection;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.rest.dto.company.RestAssignLabelToPractisSetRequest;
import com.practis.rest.dto.company.RestAssignLabelToTeamRequest;
import com.practis.rest.dto.company.RestTeamAddMembersRequest;
import com.practis.rest.dto.company.RestTeamCreateRequest;
import com.practis.rest.dto.company.RestUserResponse;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestCreateChallenge;
import com.practis.rest.dto.company.library.RestCreateScenario.Scenario;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.rest.dto.user.InviteUserRequest;
import com.practis.rest.dto.user.InviteUserResponse;
import com.practis.rest.dto.user.RestLoginRequest;
import com.practis.rest.dto.user.RestLoginResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface PractisApiClientV2 {

    @RequestLine("POST /auth/login")
    @Headers("Content-Type: application/json")
    RestLoginResponse login(RestLoginRequest request);

    @RequestLine("POST /users/invite/revoke")
    @Headers("Content-Type: application/json")
    void revokeUser(List<Integer> request);

    @RequestLine("PUT /companies/archive")
    @Headers("Content-Type: application/json")
    void deactivateCompany(List<Integer> request);

    @RequestLine("PUT /companies/active/")
    @Headers("Content-Type: application/json")
    void activateCompany(List<Integer> request);

    @RequestLine(
            "GET /users/?companies={company}&limit=200&offset=0&query={query}&sort=name_asc&status=ACTIVE")
    @Headers("Content-Type: application/json")
    RestCollection<RestUserResponse> searchUser(
            @Param("query") String query, @Param("company") Integer company);

    @RequestLine("GET /users/?limit=300&offset=0&query={query}&sort=name_asc&status=ACTIVE")
    @Headers("Content-Type: application/json")
    RestCollection<RestUserResponse> searchUser(@Param("query") String query);

    @RequestLine("GET /companies?limit=20&offset=0&query={query}&sort=owner_name_asc")
    @Headers("Content-Type: application/json")
    RestCollection<RestCompanyResponse> searchCompany(@Param("query") String query);

    @RequestLine("POST /api/scenarios")
    @Headers("Content-Type: application/json")
    RestScenarioResponse createScenario(Scenario request);

    @RequestLine("PUT /scenarios/archive")
    @Headers("Content-Type: application/json")
    void archiveScenario(List<Integer> request);

    @RequestLine("DELETE /scenarios/delete")
    @Headers("Content-Type: application/json")
    void deleteScenario(List<Integer> request);

    @RequestLine("DELETE /challenges/delete")
    @Headers("Content-Type: application/json")
    void deleteChallenge(List<Integer> request);

    @RequestLine("POST /api/challenges")
    @Headers("Content-Type: application/json")
    RestChallengeResponse createChallenge(RestCreateChallenge request);

    @RequestLine(
            "GET /challenges?limit=20&offset=0&query={query}&status=ACTIVE%2C%20DRAFT&sort=updated_at_desc")
    @Headers("Content-Type: application/json")
    RestCollection<RestChallengeResponse> searchChallenge(@Param("query") String query);

    @RequestLine("PUT /challenges/archive")
    @Headers("Content-Type: application/json")
    void archiveChallenge(List<Integer> request);

    @RequestLine("GET /teams?sort=members_asc&query={query}")
    @Headers("Content-Type: application/json")
    RestCollection<NewTeamInput> searchTeam(@Param("query") String query);

    @RequestLine("DELETE /teams")
    @Headers("Content-Type: application/json")
    void deleteTeam(List<Integer> ids);

    @RequestLine("POST /teams")
    @Headers("Content-Type: application/json")
    NewTeamInput createTeam(RestTeamCreateRequest request);

    @RequestLine("POST /teams/labels/")
    @Headers("Content-Type: application/json")
    void assignLabelToTeam(List<RestAssignLabelToTeamRequest> request);

    @RequestLine("POST /practisSets/labels/")
    @Headers("Content-Type: application/json")
    void assignLabelToPractisSet(List<RestAssignLabelToPractisSetRequest> request);

    @RequestLine("POST /teams/members/")
    @Headers("Content-Type: application/json")
    void addMembersToTeam(List<RestTeamAddMembersRequest> request);

    @RequestLine("POST /users/invite")
    @Headers("Content-Type: application/json")
    List<InviteUserResponse> inviteUsers(List<InviteUserRequest> request);
}
