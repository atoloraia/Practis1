package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;

public class UsersService {

    /** Click 3-dot menu for the Users. */
    public void clickSingleAction() {
        usersPage().getThreeDotMenu().get(0).click();
    }

    /** Click bulk action for Users. */
    public void clickBulkAction() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
    }

    /** Find User labels. */
    public SelenideElement findUsersLabelCounter(final String user) {
        final var userRow = usersPage().getUserRow().find(Condition.matchText(user));
        return userRow.$("[data-test='user-labels']");
    }

    /**
     * Assert User: search, assert data on Pending list, open Profile and asserUser data.
     *
     * @return
     */
    public static GridRow searchUser(final NewUserInput inputs) {
        return userService().searchUser(inputs.getEmail());
    }
}
