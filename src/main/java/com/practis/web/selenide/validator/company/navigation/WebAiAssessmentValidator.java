package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.webAiAssessmentPage;

public class WebAiAssessmentValidator {
    /** Assert elements on empty AI Assessment page. */
    public static void assertWebEmptyAiAssessmentScreen() {
        webAiAssessmentPage().getAiAssessmentTitle().shouldBe(visible);
        webAiAssessmentPage().getAiAssessmentTitle().shouldBe(exactText("AI Assessment"));
        webAiAssessmentPage().getUpdatedTimestampText().shouldBe(visible);
        webAiAssessmentPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        webAiAssessmentPage().getUpdateButton().shouldBe(visible);
        webAiAssessmentPage().getUpdateButton().shouldBe(attribute("type", "submit"));

        webAiAssessmentPage().getSearchField().shouldBe(visible);
        webAiAssessmentPage().getSearchField().shouldBe(disabled);
        webAiAssessmentPage().getSearchField().shouldBe(attribute("type", "text"));
        webAiAssessmentPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        webAiAssessmentPage().getSearchFieldIcon().shouldBe(visible);
        webAiAssessmentPage().getFiltersButton().shouldBe(visible);
        webAiAssessmentPage().getFiltersButton().shouldBe(disabled);
        webAiAssessmentPage().getCalendarField().shouldBe(visible);
        webAiAssessmentPage().getCalendarField().shouldBe(matchText("MM/DD/YYYY"));
        webAiAssessmentPage().getPaginationCounterText().shouldBe(hidden);
        webAiAssessmentPage().getPaginationBackButton().shouldBe(visible);
        webAiAssessmentPage().getPaginationBackButton().shouldBe(disabled);
        webAiAssessmentPage().getPaginationNextButton().shouldBe(visible);
        webAiAssessmentPage().getPaginationNextButton().shouldBe(disabled);

        webAiAssessmentPage().getIdColumn().shouldBe(visible);
        webAiAssessmentPage().getIdColumn().shouldBe(exactText("#"));
        webAiAssessmentPage().getUsersColumn().shouldBe(visible);
        webAiAssessmentPage().getUsersColumn().shouldBe(exactText("Users"));
        webAiAssessmentPage().getScenarioColumn().shouldBe(visible);
        webAiAssessmentPage().getScenarioColumn().shouldBe(exactText("Scenario"));
        webAiAssessmentPage().getDateColumn().shouldBe(visible);
        webAiAssessmentPage().getDateColumn().shouldBe(exactText("Date"));
        webAiAssessmentPage().getModeColumn().shouldBe(visible);
        webAiAssessmentPage().getModeColumn().shouldBe(exactText("Mode"));
        webAiAssessmentPage().getAccuracyColumn().shouldBe(visible);
        webAiAssessmentPage().getAccuracyColumn().shouldBe(exactText("Accuracy"));
        webAiAssessmentPage().getFlagColumn().shouldBe(visible);
        webAiAssessmentPage().getNoAssessmentFilterIcon().shouldBe(visible);
        webAiAssessmentPage().getNoAssessmentFilterText().shouldBe(visible);
        webAiAssessmentPage()
                .getNoAssessmentFilterText()
                .shouldBe(exactText("No AI Assessment Logs Yet"));
    }
}
