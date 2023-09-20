package com.practis.selenide.admin.create.admin;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.adminService;
import static com.practis.web.selenide.configuration.data.NewPractisAdminData.getNewPractisAdminInput;
import static com.practis.web.selenide.validator.admin.AdminCreatePageValidator.assertElementsOnCreateAdminPage;
import static com.practis.web.selenide.validator.admin.AdminCreatePageValidator.assertErrorStateOnCreateAdminPage;
import static com.practis.web.selenide.validator.admin.AdministratorsValidator.assertAdminData;
import static com.practis.web.selenide.validator.admin.AdministratorsValidator.assertAdminGridRow;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static java.lang.String.format;

import com.practis.rest.dto.user.CreateAdminRequest;
import com.practis.rest.dto.user.InviteUserResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.AdminExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
class NewAdminTest {

    private List<String> adminEmailsToRemove;
    private CreateAdminRequest inputData;

    @BeforeEach
    void beforeEach() {
        newItemSelector().create("New Admin");

        inputData = getNewPractisAdminInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));

        adminEmailsToRemove = new ArrayList<>();
        adminEmailsToRemove.add(inputData.getEmail());
    }

    @TestRailTest(caseId = 5242)
    @DisplayName("Admin: Create: Check Elements")
    void checkElementsNewAdmin() {
        assertElementsOnCreateAdminPage();
    }

    @TestRailTest(caseId = 41)
    @DisplayName("Admin: Create")
    void adminCreation() {
        adminService().createAdmin(inputData);

        // assert message
        awaitElementExists(10, () -> snackbar().getMessage());
        snackbar().getMessage().shouldBe(exactText("Practis Admin has been created"));
        assertElementsOnCreateAdminPage();
        adminService().clickOnCrossButton();

        // assert grid row data
        final var adminGridRow =
                adminService().searchAdmin(inputData.getEmail().toLowerCase(Locale.ROOT));
        assertAdminGridRow(inputData, adminGridRow);

        // assert edit page data
        adminGridRow.click();
        assertAdminData(inputData, adminEditPage());
    }

    @TestRailTest(caseId = 42)
    @DisplayName("Admin: Create: Validation: Already used email")
    @AdminExtension
    void createAdmin_EmailAlreadyUsed(final List<InviteUserResponse> admins) {
        String existingEmail = admins.get(0).getFirstName();

        adminService().createAdmin(existingEmail);

        // assert error state
        assertErrorStateOnCreateAdminPage();
    }

    @AfterEach
    void cleanup() {
        adminEmailsToRemove.forEach(email -> practisApi().deleteAdmin(email));
    }
}
