package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.webTrainingLogPage;

public class WebTrainingLogValidator {
    /** Assert elements on empty AI Assessment page. */
    public static void assertWebEmptyTrainingLogScreen() {
        webTrainingLogPage().getTrainingLogTitle().shouldBe(visible);
        webTrainingLogPage().getTrainingLogTitle().shouldBe(exactText("Training Log"));
        webTrainingLogPage().getUpdatedTimestampText().shouldBe(visible);
        webTrainingLogPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        webTrainingLogPage().getUpdateButton().shouldBe(visible);
        webTrainingLogPage().getUpdateButton().shouldBe(attribute("type", "submit"));

        webTrainingLogPage().getSearchField().shouldBe(visible);
        webTrainingLogPage().getSearchField().shouldBe(disabled);
        webTrainingLogPage().getSearchField().shouldBe(attribute("type", "text"));
        webTrainingLogPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        webTrainingLogPage().getSearchFieldIcon().shouldBe(visible);
        webTrainingLogPage().getFiltersButton().shouldBe(visible);
        webTrainingLogPage().getFiltersButton().shouldBe(disabled);
        webTrainingLogPage().getCalendarField().shouldBe(visible);
        webTrainingLogPage().getCalendarField().shouldBe(matchText("MM/DD/YYYY"));
        webTrainingLogPage().getPaginationCounterText().shouldBe(hidden);
        webTrainingLogPage().getPaginationBackButton().shouldBe(visible);
        webTrainingLogPage().getPaginationBackButton().shouldBe(disabled);
        webTrainingLogPage().getPaginationNextButton().shouldBe(visible);
        webTrainingLogPage().getPaginationNextButton().shouldBe(disabled);

        webTrainingLogPage().getIdColumn().shouldBe(visible);
        webTrainingLogPage().getIdColumn().shouldBe(exactText("#"));
        webTrainingLogPage().getUsersColumn().shouldBe(visible);
        webTrainingLogPage().getUsersColumn().shouldBe(exactText("Users"));
        webTrainingLogPage().getScenarioColumn().shouldBe(visible);
        webTrainingLogPage().getScenarioColumn().shouldBe(exactText("Scenario"));
        webTrainingLogPage().getDateColumn().shouldBe(visible);
        webTrainingLogPage().getRepColumn().shouldBe(exactText("Rep"));
        webTrainingLogPage().getRepColumn().shouldBe(visible);
        webTrainingLogPage().getDateColumn().shouldBe(exactText("Date"));
        webTrainingLogPage().getModeColumn().shouldBe(visible);
        webTrainingLogPage().getModeColumn().shouldBe(exactText("Mode"));
        webTrainingLogPage().getAccuracyColumn().shouldBe(visible);
        webTrainingLogPage().getAccuracyColumn().shouldBe(exactText("Accuracy"));
        webTrainingLogPage().getFlagColumn().shouldBe(visible);
        webTrainingLogPage().getNoTrainingLogFilterIcon().shouldBe(visible);
        webTrainingLogPage().getNoTrainingLogFilterText().shouldBe(visible);
        webTrainingLogPage()
                .getNoTrainingLogFilterText()
                .shouldBe(exactText("No Training Log Yet"));
    }
}
