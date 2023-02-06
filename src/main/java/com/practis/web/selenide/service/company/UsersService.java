package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;

public class UsersService {

    /** Click 3-dot menu for the Users - Registered. */
    public void clickSingleActionUsersRegistered() {
        usersPage().getThreeDotMenu().get(0).click();
    }

    /** Click on 3-dot menu for the Users - Registered - View Profile. */
    public void clickUsersRegisteredSingleActionViewProfile(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getViewProfileAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - User Settings. */
    public void clickUsersRegisteredSingleActionUserSettings(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getUserSettingsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Assign PSs. */
    public void clickUsersRegisteredSingleActionAssignPs() {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getAssignPractisSetsAction().click();
    }

    /** Click on 3-dot menu for the Users - Registered - Assign Labels. */
    public void clickUsersRegisteredSingleActionAssignLabels(final String user) {
        usersPage().getThreeDotMenu().get(0).click();
        usersPage().getAssignLabelsAction().click();
    }
}
