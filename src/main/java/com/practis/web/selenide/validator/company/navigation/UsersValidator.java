package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;

import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;

public class UsersValidator {

    /** Assert grid row with input data. */
    public static void assertUserGridRowPending(
            final NewUserInput inputData, final GridRow gridRow) {
        gridRow.get("Users")
                .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
        gridRow.get("Email Address").shouldBe(matchText(inputData.getEmail()));
    }

    /** Assert Users - Registered list. */
    public static void assertUsersRegisteredPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getSelectedTab().get(0).shouldBe(visible);
        usersPage().getSelectedTab().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getFiltersButton().shouldBe(enabled);
        usersPage().getItemsCounterText().shouldBe(visible);
        usersPage().getItemsCounterText().shouldBe(matchText("Items"));
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPage().getListColumns().get(0).shouldBe(visible);
        usersPage().getListColumns().get(0).shouldBe(exactText("Users"));
        usersPage().getListColumns().get(1).shouldBe(visible);
        usersPage().getListColumns().get(1).shouldBe(exactText("Teams"));
        usersPage().getListColumns().get(2).shouldBe(visible);
        usersPage().getListColumns().get(2).shouldBe(exactText("Practis Sets"));
        usersPage().getListColumns().get(3).shouldBe(visible);
        usersPage().getListColumns().get(3).shouldBe(exactText("Role"));
        usersPage().getListColumns().get(4).shouldBe(visible);
        usersPage().getListColumns().get(4).shouldBe(exactText("Registered on"));
        usersPage().getListColumns().get(5).shouldBe(visible);
        usersPage().getListColumns().get(5).shouldBe(exactText("Last Login"));

        usersPage().getListValues().get(0).shouldBe(visible);
        usersPage().getListValues().get(0).shouldBe(attribute("width", "0.1"));
        usersPage().getListValues().get(1).shouldBe(visible);
        usersPage().getListValues().get(2).shouldBe(visible);
        usersPage().getListValues().get(3).shouldBe(visible);
        usersPage().getListValues().get(4).shouldBe(visible);
        usersPage().getListValues().get(5).shouldBe(visible);

        usersPage().getLabelsIcon().get(0).shouldBe(visible);
        usersPage().getThreeDotMenu().shouldBe(visible);
    }

    /** Assert Users - Empty Pending list. */
    public static void assertUsersPendingPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getTabs().get(0).shouldBe(visible);
        usersPage().getTabs().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getFiltersButton().shouldBe(enabled);
        usersPage().getItemsCounterText().shouldBe(visible);
        usersPage().getItemsCounterText().shouldBe(matchText("Items"));
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPage().getListColumns().get(0).shouldBe(visible);
        usersPage().getListColumns().get(0).shouldBe(exactText("Users"));
        usersPage().getListColumns().get(1).shouldBe(visible);
        usersPage().getListColumns().get(1).shouldBe(exactText("Email Address"));
        usersPage().getListColumns().get(2).shouldBe(visible);
        usersPage().getListColumns().get(2).shouldBe(exactText("Role"));
        usersPage().getListColumns().get(3).shouldBe(visible);
        usersPage().getListColumns().get(3).shouldBe(exactText("Invited by"));
        usersPage().getListColumns().get(4).shouldBe(visible);
        usersPage().getListColumns().get(4).shouldBe(exactText("Invited on"));

        usersPage().getListValues().get(0).shouldBe(visible);
        usersPage().getListValues().get(0).shouldBe(attribute("width", "0.1"));
        usersPage().getListValues().get(1).shouldBe(visible);
        usersPage().getListValues().get(2).shouldBe(visible);
        usersPage().getListValues().get(3).shouldBe(visible);
        usersPage().getListValues().get(4).shouldBe(visible);

        usersPage().getLabelsIcon().get(0).shouldBe(visible);
        usersPage().getThreeDotMenu().shouldBe(visible);

        usersPage().getNoUsersFoundIcon().shouldBe(hidden);
        usersPage().getNoUsersFoundText().shouldBe(hidden);
    }

    /** Assert Users - Empty Pending list. */
    public static void assertUsersEmptyPendingPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getTabs().get(0).shouldBe(visible);
        usersPage().getTabs().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getDisabledFiltersButton().shouldBe(visible);
        usersPage().getDisabledFiltersButton().shouldBe(disabled);
        usersPage().getItemsCounterText().shouldBe(hidden);
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPage().getDisabledListColumns().get(0).shouldBe(visible);
        usersPage().getDisabledListColumns().get(0).shouldBe(exactText("Users"));
        usersPage().getDisabledListColumns().get(1).shouldBe(visible);
        usersPage().getDisabledListColumns().get(1).shouldBe(exactText("Email Address"));
        usersPage().getDisabledListColumns().get(2).shouldBe(visible);
        usersPage().getDisabledListColumns().get(2).shouldBe(exactText("Role"));
        usersPage().getDisabledListColumns().get(3).shouldBe(visible);
        usersPage().getDisabledListColumns().get(3).shouldBe(exactText("Invited by"));
        usersPage().getDisabledListColumns().get(4).shouldBe(visible);
        usersPage().getDisabledListColumns().get(4).shouldBe(exactText("Invited on"));

        usersPage().getNoUsersFoundIcon().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(exactText("No Pending Users Yet"));
    }

    /** Assert Users - Drafts list. */
    public static void assertUsersDraftsPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getTabs().get(0).shouldBe(visible);
        usersPage().getTabs().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getFiltersButton().shouldBe(enabled);
        usersPage().getItemsCounterText().shouldBe(visible);
        usersPage().getItemsCounterText().shouldBe(matchText("Items"));
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPage().getListColumns().get(0).shouldBe(visible);
        usersPage().getListColumns().get(0).shouldBe(exactText("Drafts"));
        usersPage().getListColumns().get(1).shouldBe(visible);
        usersPage().getListColumns().get(1).shouldBe(exactText("Users"));
        usersPage().getListColumns().get(2).shouldBe(visible);
        usersPage().getListColumns().get(2).shouldBe(exactText("Created by"));
        usersPage().getListColumns().get(3).shouldBe(visible);
        usersPage().getListColumns().get(3).shouldBe(exactText("Created on"));
        usersPage().getListColumns().get(4).shouldBe(visible);
        usersPage().getListColumns().get(4).shouldBe(exactText("Edited by"));
        usersPage().getListColumns().get(5).shouldBe(visible);
        usersPage().getListColumns().get(5).shouldBe(exactText("Edited on"));

        usersPage().getListValues().get(0).shouldBe(visible);
        usersPage().getListValues().get(0).shouldBe(attribute("width", "0.1"));
        usersPage().getListValues().get(1).shouldBe(visible);
        usersPage().getListValues().get(2).shouldBe(visible);
        usersPage().getListValues().get(3).shouldBe(visible);
        usersPage().getListValues().get(4).shouldBe(visible);
        usersPage().getListValues().get(5).shouldBe(visible);
    }

    /** Assert Users - Empty Drafts list. */
    public static void assertUsersEmptyDraftsPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getTabs().get(0).shouldBe(visible);
        usersPage().getTabs().get(0).shouldBe(exactText("Registered"));
        usersPage().getTabs().get(1).shouldBe(visible);
        usersPage().getTabs().get(1).shouldBe(exactText("Pending"));
        usersPage().getTabs().get(2).shouldBe(visible);
        usersPage().getTabs().get(2).shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchField().shouldBe(enabled);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getDisabledFiltersButton().shouldBe(visible);
        usersPage().getDisabledFiltersButton().shouldBe(disabled);
        usersPage().getItemsCounterText().shouldBe(hidden);
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);

        usersPage().getDisabledListColumns().get(0).shouldBe(visible);
        usersPage().getDisabledListColumns().get(0).shouldBe(exactText("Drafts"));
        usersPage().getDisabledListColumns().get(1).shouldBe(visible);
        usersPage().getDisabledListColumns().get(1).shouldBe(exactText("Users"));
        usersPage().getDisabledListColumns().get(2).shouldBe(visible);
        usersPage().getDisabledListColumns().get(2).shouldBe(exactText("Created by"));
        usersPage().getDisabledListColumns().get(3).shouldBe(visible);
        usersPage().getDisabledListColumns().get(3).shouldBe(exactText("Created on"));
        usersPage().getDisabledListColumns().get(4).shouldBe(visible);
        usersPage().getDisabledListColumns().get(4).shouldBe(exactText("Edited by"));
        usersPage().getDisabledListColumns().get(5).shouldBe(visible);
        usersPage().getDisabledListColumns().get(5).shouldBe(exactText("Edited on"));

        usersPage().getNoUsersFoundIcon().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(exactText("No Drafts Yet"));
    }
}
