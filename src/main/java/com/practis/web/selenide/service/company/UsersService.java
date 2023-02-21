package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;

public class UsersService {

    /** Click 3-dot menu for the Users. */
    public void clickSingleAction() {
        usersPage().getThreeDotMenu().get(0).click();
    }

    /** Click bulk action for Users. */
    public void clickBulkAction() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().click();
    }
}
