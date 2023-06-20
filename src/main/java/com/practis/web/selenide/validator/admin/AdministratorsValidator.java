package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminEditPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.administratorsPage;
import static com.practis.web.selenide.validator.common.SearchValidator.assertNoSearchResult;
import static java.util.Locale.ROOT;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.practis.rest.dto.user.InviteUserRequest;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.admin.AdminEditPage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AdministratorsValidator {

    /** Assert grid row with input data. */
    public static void assertAdminGridRow(
            final InviteUserRequest inputData, final GridRow gridRow) {
        gridRow.get("Administrators")
                .shouldBe(text(inputData.getFirstName() + " " + inputData.getLastName()));
        gridRow.get("Email Address").shouldBe(exactText(inputData.getEmail().toLowerCase(ROOT)));
    }

    /** Assert data on edit page with input. */
    public static void assertAdminData(
            final InviteUserRequest inputData, final AdminEditPage editPage) {
        editPage.getFirstNameField().shouldBe(attribute("value", inputData.getFirstName()));
        editPage.getLastNameField().shouldBe(attribute("value", inputData.getLastName()));
        editPage.getEmailField()
                .shouldBe(attribute("value", inputData.getEmail().toLowerCase(ROOT)));

        editPage.getEmailInfo().shouldBe(exactText(inputData.getEmail()));

        editPage.getNameInfo()
                .shouldBe(exactText(inputData.getFirstName() + " " + inputData.getLastName()));
    }

    /** Assert elements on User Settings page. */
    public static void assertElementsOnUserSettingsPage() {

        adminEditPage().getUserSettingsTitle().shouldBe(visible);
        adminEditPage().getUserSettingsTitle().shouldBe(exactText("User Settings"));
        adminEditPage().getUserName().shouldBe(visible);
        adminEditPage().getBackButton().shouldBe(visible);

        companySelector().getCompanySelector().shouldBe(visible);
        companySelector().getCompanySelector().shouldBe(exactText("Practis"));
        newItemSelector().getNewItemSelector().shouldBe(visible);

        adminEditPage().getRoleTitle().shouldBe(visible);
        adminEditPage().getRoleTitle().shouldBe(exactText("Practis Admin"));
        adminEditPage().getSmallUserPic().shouldBe(visible);
        adminEditPage().getNameInfo().shouldBe(visible);
        String userName = adminEditPage().getUserName().text();
        adminEditPage().getNameInfo().shouldBe(matchText(userName));
        adminEditPage().getEmailInfo().shouldBe(visible);

        adminEditPage().getChangePasswordButton().shouldBe(visible);
        adminEditPage().getChangePasswordButton().shouldBe(exactText("Change Password"));

        adminEditPage().getLargeUserPic().shouldBe(visible);
        adminEditPage().getUploadPictureButton().shouldBe(exactText("Upload a new picture"));
        adminEditPage().getUploadPictureButton().shouldBe(visible);
        adminEditPage().getPictureText().shouldBe(exactText("JPG, PNG, BMP only. Less than 10 MB"));
        adminEditPage().getLargeUserPic().shouldBe(attribute("height", "136"));
        adminEditPage().getLargeUserPic().shouldBe(attribute("width", "136"));

        adminEditPage().getFirstNameField().shouldBe(visible);
        adminEditPage().getFirstNameField().shouldBe(attribute("type", "text"));
        adminEditPage().getFirstNameField().shouldBe(attribute("maxlength", "100"));

        adminEditPage().getLastNameField().shouldBe(visible);
        adminEditPage().getLastNameField().shouldBe(attribute("type", "text"));
        adminEditPage().getLastNameField().shouldBe(attribute("maxlength", "100"));

        adminEditPage().getEmailField().shouldBe(visible);
        adminEditPage().getEmailField().shouldBe(attribute("type", "email"));
        adminEditPage().getEmailField().shouldBe(disabled);

        adminEditPage().getMobileField().shouldBe(visible);
        adminEditPage().getMobileField().shouldBe(attribute("width", "100%"));
        adminEditPage().getMobileField().shouldBe(attribute("font-size", "13px"));

        adminEditPage().getDeleteButton().shouldBe(visible);
        adminEditPage().getDeleteButton().shouldBe(attribute("type", "button"));
        adminEditPage().getDeleteButton().shouldBe(attribute("color", "warning"));
        adminEditPage().getDeleteButton().shouldBe(attribute("width", "120px"));
        adminEditPage().getDeleteButton().shouldBe(exactText("Delete"));

        adminEditPage().getUpdateButton().shouldBe(visible);
        adminEditPage().getUpdateButton().shouldBe(disabled);
        adminEditPage().getUpdateButton().shouldBe(attribute("type", "submit"));
        adminEditPage().getUpdateButton().shouldBe(attribute("color", "default"));
        adminEditPage().getUpdateButton().shouldBe(attribute("width", "120px"));
        adminEditPage().getUpdateButton().shouldBe(exactText("Update"));

        adminEditPage().getChangePasswordButton().click();

        adminEditPage().getNewPasswordField().shouldBe(visible);
        adminEditPage().getNewPasswordField().shouldBe(attribute("type", "password"));
        adminEditPage().getNewPasswordField().shouldBe(attribute("autocomplete", "new-password"));
        adminEditPage().getNewPasswordField().sibling(0).shouldBe(exactText("New Password"));

        adminEditPage().getShowNewPasswordButton().shouldBe(visible);
        adminEditPage().getShowNewPasswordButton().shouldBe(exactText("Show"));
        adminEditPage().getShowNewPasswordButton().click();

        adminEditPage().getConfirmPasswordField().shouldBe(visible);
        adminEditPage().getConfirmPasswordField().shouldBe(attribute("name", "confirmPassword"));
        adminEditPage().getConfirmPasswordField().shouldBe(attribute("type", "password"));
        adminEditPage()
                .getConfirmPasswordField()
                .sibling(0)
                .shouldBe(exactText("Confirm Password"));
        adminEditPage().getShowConfirmPasswordButton().shouldBe(visible);
        adminEditPage().getShowConfirmPasswordButton().shouldBe(exactText("Show"));
        adminEditPage().getShowConfirmPasswordButton().click();

        adminEditPage().getUpdatePasswordButton().shouldBe(exactText("Update"));
        adminEditPage().getUpdatePasswordButton().shouldBe(attribute("color", "default"));
        adminEditPage().getUpdatePasswordButton().shouldBe(attribute("width", "120px"));
        adminEditPage().getUpdatePasswordButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert elements on Admin page. */
    public static void assertElementsOnAdministrationPage() {
        administratorsPage().getAdminHeaderText().shouldBe(exactText("Administrators"));

        search().getSearchField().shouldBe(visible);
        search().getSearchFieldIcon().shouldBe(visible);

        administratorsPage().getUpdatedTimestampText().shouldBe(visible);
        administratorsPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        administratorsPage().getUpdateButton().shouldBe(visible);

        administratorsPage().getPaginationBackButton().shouldBe(visible);
        administratorsPage().getPaginationNextButton().shouldBe(visible);
        administratorsPage().getPaginationCounterText().shouldBe(visible);
        administratorsPage().getPaginationCounterText().shouldBe(matchText("Items"));

        administratorsPage().getAdministratorColumn().shouldBe(visible);
        administratorsPage().getAdministratorColumn().shouldBe(matchText("Administrators"));
        administratorsPage().getEmailAddressColumn().shouldBe(visible);
        administratorsPage().getEmailAddressColumn().shouldBe(matchText("Email Address"));
        administratorsPage().getDateCreatedColumn().shouldBe(visible);
        administratorsPage().getDateCreatedColumn().shouldBe(matchText("Date Created"));
        administratorsPage().getOwnerColumn().shouldBe(visible);
        administratorsPage().getOwnerColumn().shouldBe(matchText("Owner"));

        companySelector().getCompanySelector().shouldBe(visible);
        companySelector().getCompanySelector().shouldBe(exactText("Practis"));
    }

    /** Assert no search results. */
    public static void assertNoSearchResultOnAdministratorsPage() {
        assertNoSearchResult();
        administratorsPage().getNoAdministratorsSearchIcon().shouldBe(visible);
        administratorsPage()
                .getNoAdministratorsSearchText()
                .shouldBe(matchText("No Administrators Found"));
        administratorsPage().getNoAdministratorsSearchIcon().shouldBe(visible);
        administratorsPage().getAdministratorItemRow().shouldBe(CollectionCondition.size(0));
    }

    /** Assert Search Results. */
    public static void assertSearchResultsOnAdministratorsPage(String input) {
        search().getSearchField().shouldBe(visible);
        administratorsPage().getAdministratorRow().shouldBe(CollectionCondition.size(1));
        final var adminRow =
                administratorsPage().getAdministratorRow().find(Condition.matchText(input));
        adminRow.shouldBe(visible);
    }

    /** Assert Search any Results. */
    public static void assertSearchAnyResultsOnAdministratorsPage() {
        search().getSearchFieldClearButton().shouldBe(visible);
        administratorsPage().getAdministratorRow().get(0).shouldBe(visible);
    }
}
