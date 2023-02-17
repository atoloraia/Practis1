package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.userSettingsPage;

public class UserSettingsValidator {

    /** Assert data on User Settings page'. */
    public static void assertUserSettingsPage(String role) {
        userSettingsPage().getUserSettingsHeader().shouldBe(visible);
        userSettingsPage().getUserSettingsHeader().shouldBe(exactText("User Settings"));
        userSettingsPage().getUserSettingsName().shouldBe(visible);
        userSettingsPage().getUserSettingsBackButton().shouldBe(visible);

        userSettingsPage().getUserPicture().shouldBe(visible);
        userSettingsPage().getUserRole().shouldBe(visible);
        userSettingsPage().getUserRole().shouldBe(exactText(role));
        userSettingsPage().getUserFullName().shouldBe(visible);
        userSettingsPage().getUserEmail().shouldBe(visible);
        userSettingsPage().getViewProfileButton().shouldBe(visible);
        userSettingsPage().getViewProfileButton().shouldBe(exactText("View Profile"));
        userSettingsPage().getViewProfileButton().shouldBe(attribute("width", "136px"));
        userSettingsPage().getViewProfileButton().shouldBe(attribute("color", "default"));

        userSettingsPage().getEditUserDetails().shouldBe(visible);
        userSettingsPage().getEditUserDetails().shouldBe(exactText("Edit User Details"));
        userSettingsPage().getChangePassword().shouldBe(visible);
        userSettingsPage().getChangePassword().shouldBe(exactText("Change Password"));
        userSettingsPage().getUploadedImageSection().shouldBe(visible);
        userSettingsPage().getUploadImageText().shouldBe(visible);
        userSettingsPage().getUploadImageText().shouldBe(exactText("Upload a new picture"));
        userSettingsPage().getUploadImageDescription().shouldBe(visible);
        userSettingsPage().getUploadImageDescription().shouldBe(matchText("JPG, PNG, BMP only."));
        userSettingsPage().getUserFirstNameField().shouldBe(visible);
        userSettingsPage().getUserLastNameField().shouldBe(visible);
        userSettingsPage().getUserEmail().shouldBe(visible);
        userSettingsPage().getUpdateButton().shouldBe(visible);
        userSettingsPage().getUpdateButton().shouldBe(exactText("Update"));
        userSettingsPage().getUpdateButton().shouldBe(attribute("width", "120px"));
        userSettingsPage().getUpdateButton().shouldBe(attribute("color", "default"));
    }
}
