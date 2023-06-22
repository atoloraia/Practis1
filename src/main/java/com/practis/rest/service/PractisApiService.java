package com.practis.rest.service;

import static com.practis.rest.configuration.PractisApiClientConfiguration.practisApiClient;
import static com.practis.rest.configuration.PractisApiV2ClientConfiguration.practisApiClientV2;
import static com.practis.rest.mapper.ChallengeMapper.toChallengeLines;
import static com.practis.rest.mapper.ChallengeMapper.toRestCreateChallenge;
import static com.practis.rest.mapper.PractisSetMapper.toRestCreatePractisSet;
import static com.practis.rest.mapper.ScenarioMapper.toScenario;
import static com.practis.rest.mapper.ScenarioMapper.toScenarioLines;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomStringUtils.random;

import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewCompanyInput;
import com.practis.dto.NewLabelInput;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewScenarioInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.RestSearchRequest;
import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.rest.dto.admin.RestCompanyRequest;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.rest.dto.company.RestAssignLabelToPractisSetRequest;
import com.practis.rest.dto.company.RestAssignLabelToTeamRequest;
import com.practis.rest.dto.company.RestAssignLabelToUserRequest;
import com.practis.rest.dto.company.RestCreateDraftUserRequest;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestDeleteDraftUserRequest;
import com.practis.rest.dto.company.RestEnrollUnEnrollRequest;
import com.practis.rest.dto.company.RestSearchLabelResponse;
import com.practis.rest.dto.company.RestStagingResponse;
import com.practis.rest.dto.company.RestTeamAddMembersRequest;
import com.practis.rest.dto.company.RestTeamCreateRequest;
import com.practis.rest.dto.company.RestUserResponse;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestCreateLabelRequest;
import com.practis.rest.dto.company.library.RestCreateScenario.Scenario;
import com.practis.rest.dto.company.library.RestPractisSetResponse;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.rest.dto.company.library.RestUserIdResponse;
import com.practis.rest.dto.user.InviteUserRequest;
import com.practis.rest.dto.user.InviteUserResponse;
import com.practis.rest.dto.user.RestLoginRequest;
import com.practis.rest.dto.user.SetCompanyRequest;
import com.practis.rest.dto.user.SignUpRequest;
import com.practis.rest.dto.user.SignUpUserResponseWrapper;
import com.practis.utils.FileUtils;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PractisApiService {

    private static String TOKEN;
    private static Integer USER_ID;

    /** Fetch auth token. Return cached if already fetched. */
    public static String getToken() {
        if (isNull(TOKEN)) {
            final var request =
                    RestLoginRequest.builder()
                            .login(webCredentialsConfig().getLogin())
                            .password(webCredentialsConfig().getPassword())
                            .build();
            final var tokenResponse = practisApiClientV2().login(request);
            TOKEN = tokenResponse.getToken();
            USER_ID = tokenResponse.getUser().getId();
        }
        return TOKEN;
    }

    public static Integer getUserId() {
        if (Objects.isNull(USER_ID)) {
            getToken();
        }
        return USER_ID;
    }

    public static void resetToken() {
        TOKEN = null;
        USER_ID = null;
    }

    /** Set admin company. */
    public static RestAdminResponse setAdminCompany(final Integer userId) {
        final var request = SetCompanyRequest.builder().companyId(null).build();
        return practisApiClient().updateUser(userId, request);
    }

    /** Set campaign. */
    public static RestAdminResponse setCompany(final Integer userId, final String companyName) {
        return practisApi()
                .findCompany(companyName)
                .map(company -> SetCompanyRequest.builder().companyId(company.getId()).build())
                .map(request -> practisApiClient().updateUser(userId, request))
                .orElseThrow(
                        () ->
                                new RuntimeException(
                                        format("Can't set company %s as active", companyName)));
    }

    /** Create new company through API. */
    public RestCompanyResponse createCompany(final NewCompanyInput input) {
        final var request =
                RestCompanyRequest.builder()
                        .name(input.getName())
                        .ownerEmail(input.getEmail())
                        .ownerFirstName(input.getFirstName())
                        .ownerLastName(input.getLastName())
                        .build();

        return practisApiClient().createCompany(List.of(request)).get(0);
    }

    /** Deactivate a company through API. */
    public void deactivateCompany(final String name) {
        findCompany(name)
                .ifPresent(
                        company ->
                                practisApiClientV2().deactivateCompany(List.of(company.getId())));
    }

    /** Activate a company through API. */
    public void activateCompany(final String name) {
        findCompany(name).ifPresent(company -> activateCompany(company.getId()));
    }

    /** Activate a company through API. */
    public void activateCompany(final Integer companyId) {
        practisApiClientV2().activateCompany(List.of(companyId));
    }

    /** Create new admin through API. */
    public InviteUserResponse createAdmin(final InviteUserRequest input) {
        final var request =
                InviteUserRequest.builder()
                        .email(input.getEmail())
                        .password(input.getPassword())
                        .firstName(input.getFirstName())
                        .lastName(input.getLastName())
                        .roleId(5)
                        .build();

        return practisApiClientV2().createAdmin(List.of(request)).get(0);
    }

    /** Delete an admin through API. */
    public void deleteAdmin(final String adminEmail) {
        findUserGlobal(adminEmail).ifPresent(admin -> practisApiClient().deleteUser(admin.getId()));
    }

    /** Delete a user through API. */
    public void deleteUser(final String userEmail) {
        findUser(userEmail).ifPresent(user -> practisApiClient().deleteUser(user.getId()));
    }

    /** Add password to user. */
    public void addPasswordToUser(final String userEmail) {
        findUser(userEmail).ifPresent(user -> practisApiClientV2().updatePassword(user.getId()));
    }

    /** Revoke a user through API. */
    public void revokeUser(final String userEmail) {
        final var company =
                findCompany(webApplicationConfig().getAutomationCompanyName())
                        .orElseThrow(() -> new RuntimeException("Company not found"))
                        .getId();
        findInvitation(company, userEmail)
                .ifPresent(user -> practisApiClientV2().revokeUser(List.of(user.getId())));
    }

    /** Create Draft User through API. */
    public RestStagingResponse createDraftUser(final RestCreateDraftUserRequest request) {
        return practisApiClient().createDraftUser(request);
    }

    /** Delete draft user through API. */
    public void deleteDraftUser(final String draftName) {
        findDraftUser(draftName)
                .ifPresent(
                        draft ->
                                practisApiClient()
                                        .deleteDraftUser(
                                                RestDeleteDraftUserRequest.builder()
                                                        .stagingIds(List.of(draft.getId()))
                                                        .build()));
    }

    /** Find first find by email. */
    public Optional<RestUserResponse> findUser(final String email) {
        final var company =
                findCompany(webApplicationConfig().getAutomationCompanyName()).orElseThrow();
        return practisApiClientV2().searchUser(email, company.getId()).getItems().stream()
                // .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    /** Find first find by id. */
    public RestUserIdResponse findUserById(final Integer id) {
        return practisApiClientV2().searchUserById(id);
    }

    /** Find first find by email. */
    public Optional<RestUserResponse> findUserGlobal(final String email) {
        return practisApiClientV2().searchUser(email).getItems().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    /** Find first find by email. */
    public Optional<RestUserResponse> findInvitation(final Integer company, final String email) {
        return practisApiClientV2().searchInvitation(company, email).getItems().stream()
                .findFirst();
    }

    /** Find first find by email. */
    public Optional<RestStagingResponse> findDraftUser(final String name) {
        final var request = RestSearchRequest.builder().searchTerm(name).build();
        return practisApiClient().searchDraftUser(request).getItems().stream().findFirst();
    }

    // Now update
    /** Find first admin by email. */
    public Optional<RestUserResponse> findPractisAdmin(final String email) {
        return practisApiClientV2().searchUser(email).getItems().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    /** Find first admin by email. */
    public Optional<RestCompanyResponse> findCompany(final String name) {
        return practisApiClientV2().searchCompany(name).getItems().stream()
                .filter(company -> company.getStatus().equalsIgnoreCase("active"))
                .filter(company -> company.getName().equals(name))
                .findFirst();
    }

    public void deleteLabel(final String name) {
        findLabel(name).ifPresent(label -> practisApiClient().deleteLabel(label.getId()));
    }

    /** Find first label by name. */
    public Optional<RestSearchLabelResponse> findLabel(final String name) {
        final var request = getRestSearchRequest(name);
        return practisApiClient().searchLabel(request).getItems().stream().findFirst();
    }

    /** Create Label through API. */
    public RestCreateLabelResponse createLabel(final NewLabelInput input) {
        final var request = RestCreateLabelRequest.builder().name(input.getName()).build();
        return practisApiClient().createLabel(request);
    }

    /** Delete Practis Set. */
    public void deletePractisSet(final String name) {
        findPractisSet(name)
                .ifPresent(
                        practisSet -> {
                            practisApiClientV2().archivePractisSet(List.of(practisSet.getId()));
                            practisApiClientV2().deletePractisSet(List.of(practisSet.getId()));
                        });
    }

    /**
     * Create Practis Set.
     *
     * @return
     */
    public RestPractisSetResponse createPractisSet(
            final NewPractisSetInput input,
            List<RestChallengeResponse> challenges,
            List<RestScenarioResponse> scenario) {
        final var request = toRestCreatePractisSet(input, challenges, scenario);
        return practisApiClient().createPractisSet(request);
    }

    /** Find first practis set by name. */
    public Optional<RestPractisSetResponse> findPractisSet(final String name) {
        return practisApiClientV2().searchPractisSet(name).getItems().stream().findFirst();
    }

    /** Find first scenario by name. */
    public Optional<RestScenarioResponse> findScenario(final String name) {
        return practisApiClientV2().searchScenario(name).getItems().stream().findFirst();
    }

    /** Create scenario. */
    public RestScenarioResponse createScenarioWithLines(final NewScenarioInput input) {
        return practisApiClientV2()
                .createScenario(
                        Scenario.builder()
                                .title(input.getTitle())
                                .description(input.getDescription())
                                .build());
    }

    /** Create scenario with lines. */
    @SneakyThrows
    public RestScenarioResponse createScenarioWithLines(
            final NewScenarioInput input, final String fileName) {
        final var audioFile =
                ofNullable(PractisApiService.class.getResource(fileName))
                        .map(FileUtils::fromResource)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                format("File '%s' not found", fileName)));
        final var request = toScenario(input);
        final var scenario = practisApiClientV2().createScenario(request);
        try {
            final var lineAudio = practisApiClient().uploadLine(audioFile, "AUDIO", "Line");
            final var lines = toScenarioLines(input, lineAudio);
            practisApiClientV2().createScenarioLines(scenario.getId(), lines);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return scenario;
    }

    /** Archive and delete scenario. */
    public void archiveAndDeleteScenario(final String name) {
        findScenario(name).ifPresent(scenario -> archiveAndDeleteScenario(scenario.getId()));
    }

    /** Archive and delete challenge. */
    public void archiveAndDeleteChallenge(final String name) {
        findChallenge(name).ifPresent(challenge -> archiveAndDeleteChallenge(challenge.getId()));
    }

    /** Archive challenge. */
    public void archiveChallenge(final String name) {
        findChallenge(name).ifPresent(challenge -> archiveChallenge(challenge.getId()));
    }

    /** Archive and delete scenario. */
    public void archiveAndDeleteScenario(final Integer id) {
        practisApiClientV2().archiveScenario(List.of(id));
        practisApiClientV2().deleteScenario(List.of(id));
    }

    /** Archive and delete challenge. */
    public void archiveAndDeleteChallenge(final Integer id) {
        practisApiClientV2().archiveChallenge(List.of(id));
        practisApiClientV2().deleteChallenge(List.of(id));
    }

    /** Archive challenge. */
    public void archiveChallenge(final Integer id) {
        practisApiClientV2().archiveChallenge(List.of(id));
    }

    /** Delete scenario. */
    public void deleteScenario(final String name) {
        findScenario(name)
                .ifPresent(
                        scenario -> {
                            practisApiClientV2().deleteScenario(List.of(scenario.getId()));
                        });
    }

    /** Delete challenge. */
    public void deleteChallenge(final String name) {
        findChallenge(name)
                .ifPresent(
                        challenge ->
                                practisApiClientV2().archiveChallenge(List.of(challenge.getId())));
    }

    /** Create challenge with lines. */
    @SneakyThrows
    public RestChallengeResponse createChallengeWithLines(
            final NewChallengeInput input, final String fileName) {
        final var audioFile =
                ofNullable(PractisApiService.class.getResource(fileName))
                        .map(FileUtils::fromResource)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                format("File '%s' not found", fileName)));
        final var request = toRestCreateChallenge(input);
        final var challenge = practisApiClientV2().createChallenge(request);
        try {
            final var lineAudio = practisApiClient().uploadLine(audioFile, "AUDIO", "Line");
            final var lines = toChallengeLines(input, lineAudio);
            practisApiClientV2().createChallengeLines(challenge.getId(), lines);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return challenge;
    }

    /** Find first challenge by name. */
    public Optional<RestChallengeResponse> findChallenge(final String name) {
        final var request = getRestSearchRequest(name);
        return practisApiClientV2().searchChallenge(name).getItems().stream().findFirst();
    }

    /** Create Team. */
    public NewTeamInput createTeam(final String name) {
        final var request = RestTeamCreateRequest.builder().name(name).build();
        return practisApiClientV2().createTeam(request);
    }

    /** Assign Label to the Team. */
    public void assignLabelToTeam(final Integer teamId, List<Integer> labelIds) {
        final var request =
                labelIds.stream()
                        .map(
                                labelId ->
                                        RestAssignLabelToTeamRequest.builder()
                                                .teamId(teamId)
                                                .labelId(labelId)
                                                .build())
                        .collect(toList());
        practisApiClientV2().assignLabelToTeam(request);
    }

    /** Assign Label to the User. */
    public void assignLabelToUser(final Integer userId, List<Integer> labelIds) {
        final var request =
                labelIds.stream()
                        .map(
                                labelId ->
                                        RestAssignLabelToUserRequest.builder()
                                                .userId(userId)
                                                .labelId(labelId)
                                                .build())
                        .collect(toList());
        practisApiClientV2().assignLabelToUser(request);
    }

    /** Assign Label to the Practis Set. */
    public void assignLabelToPractisSet(final Integer practisSetId, List<Integer> labelIds) {
        final var request =
                labelIds.stream()
                        .map(
                                labelId ->
                                        RestAssignLabelToPractisSetRequest.builder()
                                                .practisSetId(practisSetId)
                                                .labelId(labelId)
                                                .build())
                        .collect(toList());
        practisApiClientV2().assignLabelToPractisSet(request);
    }

    /** Add Members to the Team. */
    public void addMembersToTeam(final Integer teamId, List<Integer> usersIds) {
        final var request =
                usersIds.stream()
                        .map(
                                userId ->
                                        RestTeamAddMembersRequest.builder()
                                                .teamId(teamId)
                                                .userId(userId)
                                                .build())
                        .collect(toList());
        practisApiClientV2().addMembersToTeam(request);
    }

    /** Delete Team. */
    public void deleteTeam(final String name) {
        findTeam(name).ifPresent(team -> practisApiClientV2().deleteTeam(List.of(team.getId())));
    }

    /** Find Team by name. */
    public Optional<NewTeamInput> findTeam(final String name) {
        return practisApiClientV2().searchTeam(name).getItems().stream().findFirst();
    }

    /** Invite Users. */
    public List<NewUserInput> inviteUsers(final List<NewUserInput> users) {
        final var request =
                users.stream()
                        .map(
                                user ->
                                        InviteUserRequest.builder()
                                                .email(user.getEmail())
                                                .firstName(user.getFirstName())
                                                .lastName(user.getLastName())
                                                .companyId(user.getCompanyId())
                                                .roleId(user.getRoleId())
                                                .build())
                        .collect(toList());
        return practisApiClientV2().inviteUsers(request).stream()
                .map(
                        user ->
                                NewUserInput.builder()
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

    /** Assign Practis Set to the team. */
    public void assignPractisSet(
            final Integer psID, final Integer usersId, final ZonedDateTime dueDate) {
        final var request =
                List.of(
                        RestEnrollUnEnrollRequest.builder()
                                .practisSetId(psID)
                                .userId(usersId)
                                .dueDate(dueDate.plusYears(1))
                                .build());
        practisApiClientV2().enrollments(request);
    }

    /** Assign Practis Set to the team. */
    public void assignPractisSetWithDueDate(
            final Integer psID, final Integer usersId, final ZonedDateTime dueDate) {
        final var request =
                List.of(
                        RestEnrollUnEnrollRequest.builder()
                                .practisSetId(psID)
                                .userId(usersId)
                                .dueDate(dueDate.plusSeconds(1))
                                .build());
        practisApiClientV2().enrollments(request);
    }

    /** Sign Up User. */
    public List<NewUserInput> signupUsers(final List<NewUserInput> users) {
        return users.stream()
                .map(
                        inviteInfo -> {
                            final var request =
                                    SignUpRequest.builder()
                                            .firstName(inviteInfo.getFirstName())
                                            .lastName(inviteInfo.getLastName())
                                            .email(inviteInfo.getEmail())
                                            .password("qwerty123")
                                            .code(inviteInfo.getInvitationCode())
                                            .phoneNumber(random(10, false, true))
                                            .build();
                            return practisApiClientV2().signUpUser(request);
                        })
                .map(SignUpUserResponseWrapper::getUser)
                .map(
                        signUp ->
                                NewUserInput.builder()
                                        .id(signUp.getId())
                                        .email(signUp.getEmail())
                                        .firstName(signUp.getFirstName())
                                        .lastName(signUp.getLastName())
                                        .companyId(signUp.getCompanyId())
                                        .roleId(signUp.getRoleId())
                                        .phoneNumber(signUp.getPhoneNumber())
                                        .build())
                .collect(toList());
    }

    private RestSearchRequest getRestSearchRequest(final String searchTerm) {
        return RestSearchRequest.builder().searchTerm(searchTerm).build();
    }
}
