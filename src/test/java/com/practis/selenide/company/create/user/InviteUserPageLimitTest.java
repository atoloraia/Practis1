package com.practis.selenide.company.create.user;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
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
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserPageLimitTest {

    private List<String> usersToRemove;
    private NewUserInput inputData;

    @BeforeEach
    void init() {
        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

        usersToRemove = new ArrayList<>();
        usersToRemove.add(inputData.getEmail());
    }

    /** Invite User to the App: Invite Users: Check limit counters */
    @TestRailTest(caseId = 32189)
    @DisplayName("Invite User to the App: Invite Users: Check limit counters")
    @CompanyUserLimitExtension(2)
    void checkLimitsCounterOnInviteUserPage() {
        newItemSelector().create("User");

        // Check Limits
        assertLimitInfoOnInvitePage();

        // Invite User
        userService().addRow(inputData, "User");
        userService().inviteFirstUser();

        // Check Changed limits
        newItemSelector().create("User");
        assertLimitInfoOnInvitePage();
        clickOutOfTheFormForPopup();

        // Revoke User
        practisApi().revokeUser(inputData.getEmail());

        // Check Changed limits
        newItemSelector().create("User");
        assertLimitInfoOnInvitePage();
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().deleteUser(email));
    }
}
