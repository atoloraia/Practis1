package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetDetailsPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;

public class PractisSetDetailsValidator {

    /** Assert elements on Practis Set Details Page. */
    public static void assertElementsPractisSetDetailsPage() {
        practisSetDetailsPage().getPractisSetDetailsTitle().shouldBe(visible);
        practisSetDetailsPage()
                .getPractisSetDetailsTitle()
                .shouldBe(exactText("Practis Set Details"));
        practisSetDetailsPage().getPractisSetDetailsSubtitle().shouldBe(visible);
        practisSetDetailsPage().getBackArrowButton().shouldBe(visible);

        // Assign Users button
        practisSetDetailsPage().getAssignUserButton().shouldBe(visible);
        practisSetDetailsPage().getAssignUserButton().shouldBe(enabled);
        practisSetDetailsPage().getAssignUserButton().shouldBe(exactText("Assign Users"));
        practisSetDetailsPage().getAssignUserIcon().shouldBe(enabled);

        // Export Report button
        practisSetDetailsPage().getExportReportButton().shouldBe(visible);
        practisSetDetailsPage().getExportReportButton().shouldBe(enabled);
        practisSetDetailsPage().getExportReportButton().shouldBe(exactText("Export Report"));
        practisSetDetailsPage().getExportReportButton().shouldBe(enabled);
        practisSetDetailsPage().getExportReportIcon().shouldBe(visible);

        // View Practis Set button
        practisSetDetailsPage().getViewPractisSetButton().shouldBe(visible);
        practisSetDetailsPage().getViewPractisSetButton().shouldBe(enabled);
        practisSetDetailsPage().getViewPractisSetButton().shouldBe(exactText("View Practis Set"));
        practisSetDetailsPage().getViewPractisSetIcon().shouldBe(visible);

        practisSetDetailsPage().getUpdatedTimestampText().shouldBe(visible);
        practisSetDetailsPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        practisSetDetailsPage().getUpdatedTimestampButton().shouldBe(visible);

        practisSetDetailsPage().getSearchField().shouldBe(visible);
        practisSetDetailsPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        practisSetDetailsPage().getSearchFieldIcon().shouldBe(visible);
        practisSetDetailsPage().getFiltersButton().shouldBe(visible);
        practisSetDetailsPage().getItemsCounterText().shouldBe(visible);
        practisSetDetailsPage().getItemsCounterText().shouldBe(matchText("Items"));
        practisSetDetailsPage().getPrevPageButton().shouldBe(visible);
        practisSetDetailsPage().getNextPageButton().shouldBe(visible);
        practisSetDetailsPage().getPrevPageButton().shouldBe(disabled);
        practisSetDetailsPage().getNextPageButton().shouldBe(disabled);

        practisSetDetailsPage().getTableRow().get(0).shouldBe(visible);

        practisSetDetailsPage().getUserColumn().shouldBe(visible);
        practisSetDetailsPage().getUserColumn().shouldBe(exactText("Users"));
        practisSetDetailsPage().getDueDateColumn().shouldBe(visible);
        practisSetDetailsPage().getDueDateColumn().shouldBe(exactText("Due Date"));
        practisSetDetailsPage().getProgressColumn().shouldBe(visible);
        practisSetDetailsPage().getProgressColumn().shouldBe(exactText("Progress"));
        practisSetDetailsPage().getAccuracyColumn().shouldBe(visible);
        practisSetDetailsPage().getAccuracyColumn().shouldBe(exactText("Accuracy"));
        practisSetDetailsPage().getTrainingTimeColumn().shouldBe(visible);
        practisSetDetailsPage().getTrainingTimeColumn().shouldBe(exactText("Training Time"));
        practisSetDetailsPage().getAssignedColumn().shouldBe(visible);
        practisSetDetailsPage().getAssignedColumn().shouldBe(exactText("Assigned"));
        practisSetDetailsPage().getStartedColumn().shouldBe(visible);
        practisSetDetailsPage().getStartedColumn().shouldBe(exactText("Started"));
        practisSetDetailsPage().getLastTrainingColumn().shouldBe(visible);
        practisSetDetailsPage().getLastTrainingColumn().shouldBe(exactText("Last Training"));

        practisSetDetailsPage().getAccuracyQuestionMarkButton().shouldBe(visible);
        practisSetDetailsPage().getProgressQuestionMarkButton().shouldBe(visible);
        practisSetDetailsPage().getTrainingTimeQuestionMarkButton().shouldBe(visible);
    }

    /** Assert Search field on Practis Set Details page. */
    public static void assertSearchFieldOnPractisSetDetailsPage() {
        practisSetDetailsPage().getSearchField().shouldBe(visible);
        practisSetDetailsPage().getSearchField().shouldBe(enabled);
        practisSetDetailsPage().getSearchFieldIcon().shouldBe(visible);
        practisSetDetailsPage().getSearchFieldCrossButton().shouldBe(hidden);
    }

    /** Assert Search Results. */
    public static void assertSearchResultsOnPractisSetDetailsPage() {
        assertElementsPractisSetDetailsPage();
        practisSetDetailsPage().getSearchFieldCrossButton().shouldBe(visible);
        practisSetDetailsPage().getTableRow().get(0).shouldBe(visible);
        practisSetDetailsPage().getItemsCounterText().shouldBe(exactText("1-1 of 1 Items"));
        practisSetDetailsPage().getSearchFieldCrossButton().click();
        practisSetDetailsPage().getSearchFieldCrossButton().shouldBe(hidden);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharPractisSetDetailsPage(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        practisSetDetailsPage().getSearchField().append(String.valueOf(input));
        assertSearchResultsOnPractisSetDetailsPage();
    }

    /** Assert no search results. */
    public static void assertNoSearchResultPractisSetDetailsPage() {
        practisSetDetailsPage().getNoSearchIcon().shouldBe(visible);
        practisSetDetailsPage().getNoSearchText().shouldBe(visible);
        practisSetDetailsPage().getNoSearchText().shouldBe(exactText("No Users Found"));
        practisSetDetailsPage().getSearchFieldCrossButton().shouldBe(visible);

        practisSetDetailsPage().getItemsCounterText().shouldBe(visible);
        practisSetDetailsPage().getItemsCounterText().shouldBe(exactText("0 Items"));
        practisSetDetailsPage().getFiltersButton().shouldBe(visible);
        practisSetDetailsPage().getFiltersButton().shouldBe(disabled);
        practisSetDetailsPage().getPrevPageButton().shouldBe(visible);
        practisSetDetailsPage().getPrevPageButton().shouldBe(disabled);
        practisSetDetailsPage().getNextPageButton().shouldBe(visible);
        practisSetDetailsPage().getNextPageButton().shouldBe(disabled);
        practisSetDetailsPage().getAssignUserButton().shouldBe(enabled);
        practisSetDetailsPage().getExportReportButton().shouldBe(disabled);
        practisSetDetailsPage().getViewPractisSetIcon().shouldBe(enabled);

        practisSetDetailsPage().getUserColumn().shouldBe(visible);
        practisSetDetailsPage().getUserColumn().shouldBe(attribute("disabled"));
        practisSetDetailsPage().getUserColumn().shouldBe(exactText("Users"));
        practisSetDetailsPage().getDueDateColumn().shouldBe(visible);
        practisSetDetailsPage().getDueDateColumn().shouldBe(attribute("disabled"));
        practisSetDetailsPage().getDueDateColumn().shouldBe(exactText("Due Date"));
        practisSetDetailsPage().getProgressColumn().shouldBe(visible);
        practisSetDetailsPage().getProgressColumn().shouldBe(attribute("disabled"));
        practisSetDetailsPage().getProgressColumn().shouldBe(exactText("Progress"));
        practisSetDetailsPage().getAccuracyColumn().shouldBe(visible);
        practisSetDetailsPage().getAccuracyColumn().shouldBe(attribute("disabled"));
        practisSetDetailsPage().getAccuracyColumn().shouldBe(exactText("Accuracy"));
        practisSetDetailsPage().getTrainingTimeColumn().shouldBe(visible);
        practisSetDetailsPage().getTrainingTimeColumn().shouldBe(attribute("disabled"));
        practisSetDetailsPage().getTrainingTimeColumn().shouldBe(exactText("Training Time"));
        practisSetDetailsPage().getAssignedColumn().shouldBe(visible);
        practisSetDetailsPage().getAssignedColumn().shouldBe(attribute("disabled"));
        practisSetDetailsPage().getAssignedColumn().shouldBe(exactText("Assigned"));
        practisSetDetailsPage().getStartedColumn().shouldBe(visible);
        practisSetDetailsPage().getStartedColumn().shouldBe(attribute("disabled"));
        practisSetDetailsPage().getStartedColumn().shouldBe(exactText("Started"));
        practisSetDetailsPage().getLastTrainingColumn().shouldBe(visible);
        practisSetDetailsPage().getLastTrainingColumn().shouldBe(attribute("disabled"));
        practisSetDetailsPage().getLastTrainingColumn().shouldBe(exactText("Last Training"));

        practisSetDetailsPage().getTableRow().shouldBe(CollectionCondition.size(0));
        practisSetDetailsPage().getSearchFieldCrossButton().click();
    }

    /** Assert clean search on Team - Practis Set Details page. */
    public static void assertCleanSearchPractisSetDetailsPage(int userRow) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        practisSetDetailsPage().getSearchFieldCrossButton().shouldNotBe(visible);
        practisSetDetailsPage().getTableRow().shouldHave(CollectionCondition.size(userRow));
        practisSetDetailsPage().getSearchField().append(("check clean icon"));
        practisSetDetailsPage().getSearchFieldIcon().shouldBe(visible);
        practisSetDetailsPage().getTableRow().shouldHave(CollectionCondition.size(0));
        practisSetDetailsPage().getSearchFieldCrossButton().click();
        practisSetDetailsPage().getSearchFieldCrossButton().shouldNotBe(visible);
        practisSetDetailsPage().getTableRow().shouldHave(CollectionCondition.size(userRow));
    }

    /** Assert clean search on Team - Practis Set Details page. */
    public static void assertFiltersModalPSDetailsPage() {

        // Registration Status
        practisSetDetailsPage().getRegistrationStatusTitle().shouldBe(visible);
        practisSetDetailsPage()
                .getRegistrationStatusTitle()
                .shouldBe(matchText("Registration Status"));
        practisSetDetailsPage()
                .getRegistrationStatusItems()
                .shouldHave(CollectionCondition.size(2));
        practisSetDetailsPage().getActiveCheckbox().shouldBe(visible);
        practisSetDetailsPage().getActiveCheckbox().shouldBe(attribute("size", "12"));
        practisSetDetailsPage().getPendingCheckbox().shouldBe(visible);
        practisSetDetailsPage().getPendingCheckbox().shouldBe(attribute("size", "12"));
        practisSetDetailsPage().getActiveText().shouldBe(visible);
        practisSetDetailsPage().getActiveText().shouldBe(exactText("Active"));
        practisSetDetailsPage().getActiveText().shouldBe(attribute("color", "#212121"));
        practisSetDetailsPage().getPendingText().shouldBe(visible);
        practisSetDetailsPage().getPendingText().shouldBe(exactText("Pending"));
        practisSetDetailsPage().getPendingText().shouldBe(attribute("color", "#212121"));

        // Progress Status
        practisSetDetailsPage().getProgressStatusTitle().shouldBe(visible);
        practisSetDetailsPage().getProgressStatusTitle().shouldBe(exactText("Progress Status"));
        practisSetDetailsPage().getProgressStatusItems().shouldHave(CollectionCondition.size(3));

        practisSetDetailsPage().getNotStartedText().shouldBe(visible);
        practisSetDetailsPage().getNotStartedText().shouldBe(exactText("Not Started"));
        practisSetDetailsPage().getNotStartedText().shouldBe(attribute("Color", "#212121"));
        practisSetDetailsPage().getNotStartedCheckbox().shouldBe(visible);
        practisSetDetailsPage().getNotStartedCheckbox().shouldBe(attribute("size", "12"));

        practisSetDetailsPage().getInProgressText().shouldBe(visible);
        practisSetDetailsPage().getInProgressText().shouldBe(exactText("In Progress"));
        practisSetDetailsPage().getInProgressText().shouldBe(attribute("Color", "#212121"));
        practisSetDetailsPage().getInProgressCheckbox().shouldBe(visible);
        practisSetDetailsPage().getInProgressCheckbox().shouldBe(attribute("size", "12"));

        practisSetDetailsPage().getCompletedText().shouldBe(visible);
        practisSetDetailsPage().getCompletedText().shouldBe(exactText("Completed"));
        practisSetDetailsPage().getCompletedText().shouldBe(attribute("Color", "#212121"));
        practisSetDetailsPage().getCompletedCheckbox().shouldBe(visible);
        practisSetDetailsPage().getCompletedCheckbox().shouldBe(attribute("size", "12"));

        // Due Date
        practisSetDetailsPage().getDueDateTitle().shouldBe(visible);
        practisSetDetailsPage().getDueDateTitle().shouldBe(exactText("Due Date"));

        practisSetDetailsPage().getAssignedTitle().shouldBe(visible);
        practisSetDetailsPage().getAssignedTitle().shouldBe(exactText("Assigned"));
        practisSetDetailsPage().getAssignedTitle().shouldBe(attribute("Color", "#212121"));
        practisSetDetailsPage().getAssignedCheckbox().shouldBe(visible);
        practisSetDetailsPage().getAssignedCheckbox().shouldBe(attribute("size", "12"));

        practisSetDetailsPage().getNoDueDateText().shouldBe(visible);
        practisSetDetailsPage().getNoDueDateText().shouldBe(exactText("No Due Date"));
        practisSetDetailsPage().getNoDueDateText().shouldBe(attribute("Color", "#212121"));
        practisSetDetailsPage().getNoDueDateCheckbox().shouldBe(visible);
        practisSetDetailsPage().getNoDueDateCheckbox().shouldBe(attribute("size", "12"));

        practisSetDetailsPage().getOverdueText().shouldBe(visible);
        practisSetDetailsPage().getOverdueText().shouldBe(exactText("Overdue"));
        practisSetDetailsPage().getOverdueText().shouldBe(attribute("Color", "#212121"));
        practisSetDetailsPage().getOverdueCheckbox().shouldBe(visible);
        practisSetDetailsPage().getOverdueCheckbox().shouldBe(attribute("size", "12"));
        practisSetDetailsPage().getOverdueIcon().shouldBe(visible);
    }
}
