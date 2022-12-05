package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.aiAssessmentPage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AiAssessmentValidator {

    /** Assert elements on AI Assessment page. */
    public static void assertElementsOnAiAssessmentPage() {
        aiAssessmentPage().getAlAssessmentTitle().shouldBe(visible);
        aiAssessmentPage().getAlAssessmentTitle().shouldBe(exactText("AI Assessment"));
        aiAssessmentPage().getUpdatedTimestampText().shouldBe(visible);
        aiAssessmentPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        aiAssessmentPage().getUpdateButton().shouldBe(visible);
        aiAssessmentPage().getUpdateButton().shouldBe(attribute("type", "submit"));

        aiAssessmentPage().getSearchField().shouldBe(visible);
        aiAssessmentPage().getSearchField().shouldBe(enabled);
        aiAssessmentPage().getSearchField().shouldBe(attribute("type", "text"));
        aiAssessmentPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        aiAssessmentPage().getSearchFieldIcon().shouldBe(visible);
        aiAssessmentPage().getFiltersButton().shouldBe(visible);
        aiAssessmentPage().getFiltersButton().shouldBe(enabled);
        aiAssessmentPage().getCalendarField().shouldBe(visible);
        aiAssessmentPage().getCalendarField().shouldBe(enabled);
        aiAssessmentPage().getCalendarField().shouldBe(matchText("MM/DD/YYYY"));
        aiAssessmentPage().getPaginationCounterText().shouldBe(visible);
        aiAssessmentPage().getPaginationCounterText().shouldBe(matchText("Items"));
        aiAssessmentPage().getPaginationBackButton().shouldBe(visible);
        aiAssessmentPage().getPaginationBackButton().shouldBe(disabled);
        aiAssessmentPage().getPaginationNextButton().shouldBe(visible);
        aiAssessmentPage().getPaginationNextButton().shouldBe(enabled);

        aiAssessmentPage().getIdColumn().shouldBe(visible);
        aiAssessmentPage().getIdColumn().shouldBe(exactText("#"));
        aiAssessmentPage().getIdColumn().shouldBe(attribute("width", "10"));
        aiAssessmentPage().getCompanyColumn().shouldBe(visible);
        aiAssessmentPage().getCompanyColumn().shouldBe(exactText("Company"));
        aiAssessmentPage().getCompanyColumn().shouldBe(attribute("width", "15"));
        aiAssessmentPage().getUsersColumn().shouldBe(visible);
        aiAssessmentPage().getUsersColumn().shouldBe(exactText("Users"));
        aiAssessmentPage().getUsersColumn().shouldBe(attribute("width", "15"));
        aiAssessmentPage().getScenarioColumn().shouldBe(visible);
        aiAssessmentPage().getScenarioColumn().shouldBe(exactText("Scenario"));
        aiAssessmentPage().getScenarioColumn().shouldBe(attribute("width", "20"));
        aiAssessmentPage().getRepColumn().shouldBe(visible);
        aiAssessmentPage().getRepColumn().shouldBe(exactText("Rep"));
        aiAssessmentPage().getRepColumn().shouldBe(attribute("width", "5"));
        aiAssessmentPage().getDateColumn().shouldBe(visible);
        aiAssessmentPage().getDateColumn().shouldBe(exactText("Date"));
        aiAssessmentPage().getDateColumn().shouldBe(attribute("width", "10"));
        aiAssessmentPage().getRepColumn().shouldBe(attribute("width", "5"));
        aiAssessmentPage().getModeColumn().shouldBe(visible);
        aiAssessmentPage().getModeColumn().shouldBe(exactText("Mode"));
        aiAssessmentPage().getModeColumn().shouldBe(attribute("width", "12"));
        aiAssessmentPage().getAccuracyColumn().shouldBe(visible);
        aiAssessmentPage().getAccuracyColumn().shouldBe(exactText("Accuracy"));
        aiAssessmentPage().getAccuracyColumn().shouldBe(attribute("width", "10"));
        aiAssessmentPage().getFlagColumn().shouldBe(visible);
        aiAssessmentPage().getFlagColumn().shouldBe(attribute("width", "10"));

        aiAssessmentPage().getPercentageAssessmentRow().get(0).shouldBe(visible);
        aiAssessmentPage().getPercentageAssessmentRow().get(0).shouldBe(enabled);

        aiAssessmentPage().getFlagAssessmentRow().get(0).shouldBe(visible);
        aiAssessmentPage().getFlagAssessmentRow().get(0).shouldBe(enabled);

        aiAssessmentPage().getPlayButtonAssessmentRow().get(0).shouldBe(visible);
        aiAssessmentPage().getPlayButtonAssessmentRow().get(0).shouldBe(enabled);
    }

    /** Assert elements on AI Assessment page - No Search Result. */
    public static void assertElementsOnNoSearchResultAiAssessmentPage() {
        aiAssessmentPage().getNoAssessmentSearchIcon().shouldBe(visible);
        aiAssessmentPage().getNoAssessmentSearchText().shouldBe(visible);
        aiAssessmentPage()
                .getNoAssessmentSearchText()
                .shouldBe(exactText("No AI Assessment Logs Found"));
    }
}
