package com.practis.selenide.company.create.user.action;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.draftUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertLimitInfoOnInvitePage;
import static com.practis.web.util.PractisUtils.clickOutOfTheFormForPopup;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.CompanyUserLimitExtension;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserSaveAsDraftLimitTest {

    private NewUserInput inputData;
    private List<String> draftsToRemove;

    @BeforeEach
    void init() {
        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

        draftsToRemove = new ArrayList<>();
    }

    /** Invite Draft User to the App: Check limit counters. */
    @TestRailTest(caseId = 32190)
    @DisplayName("Invite Draft User to the App: Check limit counters")
    @CompanyUserLimitExtension(2)
    @GeneratedDraftNameExtension
    void checkLimitsCounterDraftPage(String draftName) {
        // Check Limits on Invite Users screen
        newItemSelector().create("User");
        assertLimitInfoOnInvitePage();

        // Save Invitation as Draft
        userService().addRow(inputData, "User");
        userService().saveAsDraft(draftName);

        // Check Limits
        assertLimitInfoOnInvitePage();
        clickOutOfTheFormForPopup();

        // Open Draft and Invite Users
        navigationCompany().getUsersNavigationItem().click();
        usersPage().getDraftTab().click();
        draftUsersService().clickUserRow(inputData.getFirstName());
        assertLimitInfoOnInvitePage();

        // Check Limits
        //final var inputs = userService().generateUserInputs(1);
        //practisApi().inviteUsers(inputs);
    }

    @AfterEach
    void cleanup() {
        draftsToRemove.forEach(name -> practisApi().deleteDraftUser(name));
    }
}
