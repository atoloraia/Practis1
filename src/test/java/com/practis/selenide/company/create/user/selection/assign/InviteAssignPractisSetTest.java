package com.practis.selenide.company.create.user.selection.assign;

import static com.codeborne.selenide.Condition.hidden;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.psModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.asserElementsOnPsEmptyModal;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertCleanPractisSetSearch;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertElementsOnPsSection;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertEmptyPractisSet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertPractisSetCounter;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertPsSearchAfter1Char;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSearchElementsOnPSsModal;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectAllPractisSetButton;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectedAllStatePs;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectedPractisSet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertUnSelectedAllStatePs;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertUnselectedPractisSet;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOnePractisSetSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PractisSetExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteAssignPractisSetTest {

    private List<String> usersToRemove;
    private NewUserInput inputData;

    @BeforeEach
    void init() {
        newItemSelector().create("User");
        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

        usersToRemove = new ArrayList<>();
        usersToRemove.add(inputData.getEmail());
    }

    /** Invite User to the App: Assign: Check WEB elements on PS section. */
    @TestRailTest(caseId = 31927)
    @DisplayName("Invite User to the App: Assign: PS section: Check elements")
    @PractisSetExtension(count = 1)
    void checkElementsOnPsSection() {
        Selenide.refresh();
        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();

        assertElementsOnPsSection();
    }

    /** Invite User to the App: Assign: Practis Set section: Search. */
    @TestRailTest(caseId = 31928)
    @DisplayName("Invite User to the App: Assign: PS section: Search")
    @PractisSetExtension(count = 2)
    void assignPractisSetSearch(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();

        // assert search PS
        assertSearchElementsOnPSsModal();
        // assert clean search
        assertCleanPractisSetSearch(2);
        // Search should be performed after entering 1 character
        assertPsSearchAfter1Char(practisSets.get(0).getName());
        // assert empty state
        psModuleService().searchPs("no results");
        asserElementsOnPsEmptyModal();
    }

    /** Invite User to the App: Assign: Practis Set section: Select All. */
    @TestRailTest(caseId = 31929)
    @DisplayName("Invite User to the App: Assign: PS section: Select All")
    @PractisSetExtension(count = 2)
    void assignTeamsSelectAll(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();

        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();
        // assert unselected state
        assertUnSelectedAllStatePs();
        // select one Team
        psModuleService().selectPractisSet(practisSets.get(0).getName());
        // assert modal if one Team is selected
        assertSelectedPractisSet(practisSets.get(0).getName());
        assertPractisSetCounter("1 Practis Set selected");
        assertSelectAllPractisSetButton();
        // select all
        psModuleService().selectAllPractisSets();
        assertSelectedAllStatePs();
    }

    /** Invite User to the App: Assign: Practis Set section: Cancel. */
    @TestRailTest(caseId = 31930)
    @DisplayName("Invite User to the App: Assign: PS section: Cancel")
    @PractisSetExtension(count = 1)
    void assignPractisSetCancel(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();

        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();
        // select one Practis Set and click "Cancel"
        psModuleService().selectPractisSet(practisSets.get(0).getName());
        assignUserModuleService().cancel();
        // assert User row
        assertRequiredUserGridRow(inputData, "Admin", 0);
        inviteUsersPage().getPractisSet().get(0).shouldBe(hidden);
    }

    /** Invite User to the App: Assign: Practis Set section: Apply. */
    @TestRailTest(caseId = 31931)
    @DisplayName("Invite User to the App: Assign: PS section: Apply")
    @PractisSetExtension(count = 1)
    void assignTeamsApply(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();

        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();
        // select one Practis Set and click 'Assign' button
        psModuleService().selectPractisSet(practisSets.get(0).getName());
        assignUserModuleService().apply();
        // assert User row
        assertRequiredUserGridRow(inputData, "Admin", 0);
        assertOnePractisSetSelected(0);
    }

    /** Invite User to the App: Assign: Practis Set section: Already Assigned Practis Set. */
    @TestRailTest(caseId = 31932)
    @DisplayName("Invite User to the App: Assign: PS section: Already Assigned Practis Set")
    @PractisSetExtension(count = 2)
    void assignPractisSetAlreadyAssigned(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();

        final var inputs = userService().generateUserInputs(1);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));

        userService().addRow(inputs.get(0), "Admin", practisSets.get(0));
        userService().assignAllUsers();
        // select one Practis Set and click 'Assign' button
        assertSelectedPractisSet(practisSets.get(0).getName());
        assertUnselectedPractisSet(practisSets.get(1).getName());
    }

    /** Invite User to the App: Assign: Practis Set section: Empty State. */
    @TestRailTest(caseId = 31933)
    @DisplayName("Invite User to the App: Assign: PS section: Empty state")
    void assignPractisSetEmptyState() {
        Selenide.refresh();

        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();
        assertEmptyPractisSet();
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().revokeUser(email));
    }
}
