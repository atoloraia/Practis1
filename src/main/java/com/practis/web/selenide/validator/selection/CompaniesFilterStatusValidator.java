package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companiesStatusModule;
import static com.practis.web.util.AwaitUtils.awaitSoft;

public class CompaniesFilterStatusValidator {

    /** Assert Filter: Status model on Companies page. */
    public static void assertCompaniesStatusModule() {
        awaitSoft(10, () -> companiesStatusModule().getStatusTitle().text().contains("Status"));
        companiesStatusModule().getStatusTitle().shouldHave(matchText("Status"));

        companiesStatusModule().getActiveStatus().shouldBe(exactText("Active"));
        companiesStatusModule().getCheckedActiveStatus().shouldHave(attribute("selected", "true"));
        companiesStatusModule().getInactiveStatus().shouldBe(exactText("Inactive"));
        companiesStatusModule().getCheckedInactiveStatus().shouldNot(exist);

        companiesStatusModule().getClearButton().shouldBe(enabled);
        companiesStatusModule().getClearButton().shouldBe(exactText("Clear"));
        companiesStatusModule().getApplyButton().shouldBe(enabled);
        companiesStatusModule().getApplyButton().shouldBe(exactText("Apply Filter"));
    }
}
