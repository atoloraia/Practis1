package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.logsPage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogsValidator {
    /** Assert elements on Logs page. */
    public static void assertElementsOnLogsPage() {

        logsPage().getLogsTitle().shouldBe(visible);
        logsPage().getLogsTitle().shouldBe(exactText("Event Log"));
        logsPage().getUpdatedTimestampText().shouldBe(visible);
        logsPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        logsPage().getUpdateButton().shouldBe(visible);
        logsPage().getUpdateButton().shouldBe(attribute("type", "submit"));

        logsPage().getSearchField().shouldBe(visible);
        logsPage().getSearchField().shouldBe(enabled);
        logsPage().getSearchField().shouldBe(attribute("type", "text"));
        logsPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        logsPage().getSearchFieldIcon().shouldBe(visible);
        logsPage().getFiltersButton().shouldBe(visible);
        logsPage().getFiltersButton().shouldBe(enabled);
        logsPage().getCalendarField().shouldBe(visible);
        logsPage().getCalendarField().shouldBe(enabled);
        logsPage().getCalendarField().shouldBe(matchText("MM/DD/YYYY"));
        logsPage().getPaginationCounterText().shouldBe(visible);
        logsPage().getPaginationCounterText().shouldBe(matchText("Items"));
        logsPage().getPaginationBackButton().shouldBe(visible);
        logsPage().getPaginationBackButton().shouldBe(disabled);
        logsPage().getPaginationNextButton().shouldBe(visible);
        logsPage().getPaginationNextButton().shouldBe(enabled);

        logsPage().getEventRow().get(0).shouldBe(visible);
        logsPage().getEventsColumn().shouldBe(visible);
        logsPage().getEventsColumn().shouldBe(exactText("Events"));
        logsPage().getEventsColumn().shouldBe(attribute("width", "20"));
        logsPage().getCompanyColumn().shouldBe(visible);
        logsPage().getCompanyColumn().shouldBe(exactText("Company"));
        logsPage().getCompanyColumn().shouldBe(attribute("width", "15"));
        logsPage().getCreatorColumn().shouldBe(visible);
        logsPage().getCreatorColumn().shouldBe(exactText("Creator"));
        logsPage().getCreatorColumn().shouldBe(attribute("width", "15"));
        logsPage().getRoleColumn().shouldBe(visible);
        logsPage().getRoleColumn().shouldBe(exactText("Role"));
        logsPage().getRoleColumn().shouldBe(attribute("width", "15"));
        logsPage().getDateCreatedColumn().shouldBe(visible);
        logsPage().getDateCreatedColumn().shouldBe(exactText("Date Created"));
        logsPage().getDateCreatedColumn().shouldBe(attribute("width", "10"));
        logsPage().getDetailsColumn().shouldBe(visible);
        logsPage().getDetailsColumn().shouldBe(exactText("Details"));
        logsPage().getDetailsColumn().shouldBe(attribute("width", "25"));
    }
}
