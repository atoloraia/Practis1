package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageUsersPage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.GridRow;

public class ManageUsersService {

    /** Search User on grid by User First Name. */
    public GridRow searchUser(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().userSearch(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Search User on grid by User First Name with Upper cases. */
    public GridRow searchUserWithUpperCases(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().userSearchWithUpperCases(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Search User on grid by Phone Number. */
    public GridRow searchByPhoneNumber(final String phone, final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().userSearch(phone);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Find User row by Email. */
    public SelenideElement clickOnUserRow(final String user) {
        final var userRow = manageUsersPage().getManageUsersRow().find(Condition.matchText(user));
        userRow.click();
        return userRow.$("[data-test='manage-users-email-item']");
    }

    /** Click on Clear button in Search field. */
    public static void clickOnClearButton() {
        manageUsersPage().getSearchCleanIcon().click();
    }
}
