package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingLogPage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TrainingLogValidator {

    /** Assert elements on AI Assessment page. */
    public static void assertElementsOnTrainingLogPage() {
        trainingLogPage().getTrainingLogTitle().shouldBe(visible);
        trainingLogPage().getTrainingLogTitle().shouldBe(exactText("Training Log"));
        trainingLogPage().getUpdatedTimestampText().shouldBe(visible);
        trainingLogPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        trainingLogPage().getUpdateButton().shouldBe(visible);
        trainingLogPage().getUpdateButton().shouldBe(attribute("type", "submit"));

        trainingLogPage().getSearchField().shouldBe(visible);
        trainingLogPage().getSearchField().shouldBe(enabled);
        trainingLogPage().getSearchField().shouldBe(attribute("type", "text"));
        trainingLogPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        trainingLogPage().getSearchFieldIcon().shouldBe(visible);
        trainingLogPage().getFiltersButton().shouldBe(visible);
        trainingLogPage().getFiltersButton().shouldBe(enabled);
        trainingLogPage().getCalendarField().shouldBe(visible);
        trainingLogPage().getCalendarField().shouldBe(enabled);
        trainingLogPage().getCalendarField().shouldBe(matchText("MM/DD/YYYY"));
        trainingLogPage().getPaginationCounterText().shouldBe(visible);
        trainingLogPage().getPaginationCounterText().shouldBe(matchText("Items"));
        trainingLogPage().getPaginationBackButton().shouldBe(visible);
        trainingLogPage().getPaginationBackButton().shouldBe(disabled);
        trainingLogPage().getPaginationNextButton().shouldBe(visible);
        trainingLogPage().getPaginationNextButton().shouldBe(enabled);

        trainingLogPage().getIdColumn().shouldBe(visible);
        trainingLogPage().getIdColumn().shouldBe(exactText("#"));
        trainingLogPage().getIdColumn().shouldBe(attribute("width", "10"));
        trainingLogPage().getCompanyColumn().shouldBe(visible);
        trainingLogPage().getCompanyColumn().shouldBe(exactText("Company"));
        trainingLogPage().getUsersColumn().shouldBe(visible);
        trainingLogPage().getUsersColumn().shouldBe(exactText("Users"));
        trainingLogPage().getScenarioColumn().shouldBe(visible);
        trainingLogPage().getScenarioColumn().shouldBe(exactText("Scenario"));
        trainingLogPage().getRepColumn().shouldBe(visible);
        trainingLogPage().getRepColumn().shouldBe(exactText("Rep"));
        trainingLogPage().getDateColumn().shouldBe(visible);
        trainingLogPage().getDateColumn().shouldBe(exactText("Date"));
        trainingLogPage().getModeColumn().shouldBe(visible);
        trainingLogPage().getModeColumn().shouldBe(exactText("Mode"));
        trainingLogPage().getAccuracyColumn().shouldBe(visible);
        trainingLogPage().getAccuracyColumn().shouldBe(exactText("Accuracy"));
        trainingLogPage().getFlagColumn().shouldBe(visible);

        trainingLogPage().getPercentageAssessmentRow().get(0).shouldBe(visible);
        trainingLogPage().getPercentageAssessmentRow().get(0).shouldBe(enabled);

        trainingLogPage().getFlagAssessmentRow().get(0).shouldBe(visible);
        trainingLogPage().getFlagAssessmentRow().get(0).shouldBe(enabled);

        trainingLogPage().getPlayButtonAssessmentRow().get(0).shouldBe(visible);
        trainingLogPage().getPlayButtonAssessmentRow().get(0).shouldBe(enabled);
    }

    /** Assert elements on Training Log page - No Search Result. */
    public static void assertElementsOnNoSearchResultTrainingLogPage() {
        trainingLogPage().getNoTrainingLogSearchIcon().shouldBe(visible);
        trainingLogPage().getNoTrainingLogSearchText().shouldBe(visible);
        trainingLogPage()
                .getNoTrainingLogSearchText()
                .shouldBe(exactText("No Training Logs Found"));
    }
}
