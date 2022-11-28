package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactOwnText;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.overdueModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.registrationStatus;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamMemberStatus;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetDetailsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;

public class PractisSetDetailsValidator {

  /**
   * Assert elements on Practis Set Details Page.
   */
  public static void assertElementsPractisSetDetailsPage() {
    practisSetDetailsPage().getPractisSetDetailsTitle().shouldBe(visible);
    practisSetDetailsPage().getPractisSetDetailsTitle().shouldBe(exactText("Practis Set Details"));
    practisSetDetailsPage().getPractisSetDetailsSubtitle().shouldBe(visible);
    practisSetDetailsPage().getBackArrowButton().shouldBe(visible);
    practisSetDetailsPage().getBlueActionsIcon().get(0).shouldBe(visible);
    practisSetDetailsPage().getBlueActionsIcon().get(1).shouldBe(visible);
    practisSetDetailsPage().getBlueActionsIcon().get(2).shouldBe(visible);
    practisSetDetailsPage().getBlueActionButton().get(0).shouldBe(visible);
    practisSetDetailsPage().getBlueActionButton().get(1).shouldBe(visible);
    practisSetDetailsPage().getBlueActionButton().get(2).shouldBe(visible);
    practisSetDetailsPage().getBlueActionButton().get(0).shouldBe(exactText("Assign Users"));
    practisSetDetailsPage().getBlueActionButton().get(1).shouldBe(exactText("Export Report"));
    practisSetDetailsPage().getBlueActionButton().get(2).shouldBe(exactText("View Practis Set"));
    practisSetDetailsPage().getUpdatedTimestampText().shouldBe(visible);
    practisSetDetailsPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
    practisSetDetailsPage().getUpdatedTimestampButton().shouldBe(visible);

    practisSetDetailsPage().getSearchField().shouldBe(visible);
    practisSetDetailsPage().getSearchField().shouldBe(attribute("font-size", "13px"));
    practisSetDetailsPage().getSearchFieldIcon().shouldBe(visible);
    practisSetDetailsPage().getSearchFieldCrossButton().shouldBe(hidden);
    practisSetDetailsPage().getFiltersButton().shouldBe(visible);
    practisSetDetailsPage().getItemsCounterText().shouldBe(visible);
    practisSetDetailsPage().getItemsCounterText().shouldBe(matchText("Items"));
    practisSetDetailsPage().getPrevPageButton().shouldBe(visible);
    practisSetDetailsPage().getNextPageButton().shouldBe(visible);
    practisSetDetailsPage().getPrevPageButton().shouldBe(disabled);
    practisSetDetailsPage().getNextPageButton().shouldBe(disabled);

    practisSetDetailsPage().getTableRow().get(0).shouldBe(visible);

    practisSetDetailsPage().getTableColumns().get(0).shouldBe(visible);
    practisSetDetailsPage().getTableColumns().get(0).shouldBe(exactText("Users"));
    practisSetDetailsPage().getTableColumns().get(1).shouldBe(visible);
    practisSetDetailsPage().getTableColumns().get(1).shouldBe(exactText("Due Date"));
    practisSetDetailsPage().getTableColumns().get(2).shouldBe(visible);
    practisSetDetailsPage().getTableColumns().get(2).shouldBe(exactText("Progress"));
    practisSetDetailsPage().getTableColumns().get(3).shouldBe(visible);
    practisSetDetailsPage().getTableColumns().get(3).shouldBe(exactText("Accuracy"));
    practisSetDetailsPage().getTableColumns().get(4).shouldBe(visible);
    practisSetDetailsPage().getTableColumns().get(4).shouldBe(exactText("Training Time"));
    practisSetDetailsPage().getTableColumns().get(5).shouldBe(visible);
    practisSetDetailsPage().getTableColumns().get(5).shouldBe(exactText("Assigned"));
    practisSetDetailsPage().getTableColumns().get(6).shouldBe(visible);
    practisSetDetailsPage().getTableColumns().get(6).shouldBe(exactText("Started"));
    practisSetDetailsPage().getTableColumns().get(7).shouldBe(visible);
    practisSetDetailsPage().getTableColumns().get(7).shouldBe(exactText("Last Training"));

    practisSetDetailsPage().getQuestionMarkButton().get(0).shouldBe(visible);
    practisSetDetailsPage().getQuestionMarkButton().get(1).shouldBe(visible);
    practisSetDetailsPage().getQuestionMarkButton().get(2).shouldBe(visible);

  }
}