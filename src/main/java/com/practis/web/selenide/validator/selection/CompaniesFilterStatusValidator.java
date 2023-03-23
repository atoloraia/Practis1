package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companiesStatusModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.feedStatusModule;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import com.codeborne.selenide.Condition;

public class CompaniesFilterStatusValidator {

    // TODO update

    /** Assert Status model on Companies page. */
    public static void assertCompaniesStatusModule() {
        awaitSoft(10, () -> feedStatusModule().getStatusTitle().text().contains("Status"));
        companiesStatusModule().getStatusTitle().shouldHave(Condition.text("Status"));
        companiesStatusModule().getStatusCheckbox().shouldBe(enabled);
        companiesStatusModule().getActiveStatus().shouldBe(exactText("Active"));
        // TODO should be checked Active
        companiesStatusModule().getInactiveStatus().shouldBe(exactText("Inactive"));
        // TODO should be unchecked Inactive
    }
}
