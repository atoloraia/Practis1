package com.practis.selenide.company.users;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertEmptyPendingUserProfile;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UserProfileTest {

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

    /** User Profile: Check WEB Elements. */
    @TestRailTest(caseId = 32013)
    @DisplayName("Check Elements 'User Profile' page: Empty state")
    void checkElementsProfileUser() {

        userService().fillText(inputData).selectRole("User");
        userService().addRow();

        // select user and click "Invite Selected Users" button
        userService().inviteFirstUser();

        // Check snackbar message "All Users have been invited"
        snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

        // assert grid row data
        final var userGridRow = userService().searchUser(inputData.getEmail());

        // assert data on 'User Settings' page
        awaitElementNotExists(10, () -> snackbar().getMessage());
        userGridRow.click();

        assertEmptyPendingUserProfile();
    }
}
