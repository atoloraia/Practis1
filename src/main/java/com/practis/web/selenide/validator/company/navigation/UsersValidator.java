package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.practis.dto.NewUserInput;
import com.practis.rest.dto.admin.UserStatsResponse;
import com.practis.web.selenide.component.GridRow;

public class UsersValidator {

    /** Assert grid row with input data. */
    public static void assertUserGridRow(final NewUserInput inputData, final GridRow gridRow) {
        gridRow.get("Users")
                .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
        gridRow.get("Email Address").shouldBe(matchText(inputData.getEmail()));
    }

    /** Assert empty Users list. */
    public static void assertEmptyPage() {
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getRegisteredTab().shouldBe(visible);
        usersPage().getRegisteredTab().shouldBe(exactText("Registered"));
        usersPage().getPendingTab().shouldBe(visible);
        usersPage().getPendingTab().shouldBe(exactText("Pending"));
        usersPage().getDraftTab().shouldBe(visible);
        usersPage().getDraftTab().shouldBe(exactText("Drafts"));
        usersPage().getUpdatedTimestampText().shouldBe(visible);
        usersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        usersPage().getUpdateTimestampButton().shouldBe(visible);

        usersPage().getSearchField().shouldBe(visible);
        usersPage().getSearchFieldIcon().shouldBe(visible);
        usersPage().getSearchFieldCrossButton().shouldBe(hidden);
        usersPage().getFiltersButton().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(visible);
        usersPage().getNextPageArrow().shouldBe(disabled);
        usersPage().getPreviousPageArrow().shouldBe(visible);
        usersPage().getPreviousPageArrow().shouldBe(disabled);
    }

    /** Assert Users list. */
    public static void assertUsersPage() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        usersPage().getUsersHeader().shouldBe(visible);
        usersPage().getUsersHeader().shouldBe(exactText("Users"));
        usersPage().getRegisteredTab().shouldBe(visible);
        usersPage().getRegisteredTab().shouldBe(exactText("Registered"));
        usersPage().getPendingTab().shouldBe(visible);
        usersPage().getPendingTab().shouldBe(exactText("Pending"));
        usersPage().getDraftTab().shouldBe(visible);
        usersPage().getDraftTab().shouldBe(exactText("Drafts"));
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
    }

    /** Assert assigned label view. */
    public static void assignedLabelView(final String user, final String count) {
        usersService().findUsersLabelCounter(user).shouldBe(visible);
        usersService().findUsersLabelCounter(user).shouldBe(exactText(count));
    }

    /** Assert Empty state. */
    public static void assertUsersEmptyState(String text) {
        usersPage().getNoUsersIcon().shouldBe(visible);
        usersPage().getNoUsersText().shouldBe(visible);
        usersPage().getNoUsersText().shouldBe(exactText(text));
    }

    /** Assert no search results. */
    public static void assertNoSearchResults(String text) {
        usersPage().getNoUsersFoundIcon().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(exactText(text));
    }

    /** Assert limit info */
    public static void assertLimitInfoOnUserPage() {
        UserStatsResponse stats = practisApi().getUsersStats();
        final var totalCounter = stats.getTotal();
        final var limitCounter = stats.getLimit();
        usersPage().getLimitInfoText().shouldBe(matchText(" licensed seats have been used"));
        usersPage().getTotalCounter().equals(totalCounter);
        usersPage().getLimitCounter().equals(limitCounter);
        usersPage().getLimitCounter().equals(limitCounter);
        usersPage().getLimitSettingsButton().shouldBe(visible);
    }

    /** Assert NO limit info */
    public static void assertNoLimitInfoOnUserPage() {
        usersPage().getLimitInfoText().shouldBe(hidden);
        usersPage().getLimitSettingsButton().shouldBe(hidden);
    }
}
