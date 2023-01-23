package com.practis.web.selenide.validator.company.library.practisset;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetTabService;

public class PractisSetTabValidator {

    /** Assert Action button on Practis Set Page. */
    public static void assertDisabledAssignLabelsButton() {
        practisSetTab().getAssignLabelsActionButton().shouldBe(visible);
        practisSetTab().getAssignLabelsActionButton().shouldBe(enabled);
    }

    /** Assert Label counter. */
    public static void assertLabelCountOnPsPage(final String practisSet, final String count) {
        practisSetTabService().findPsLabelCounter(practisSet).shouldBe(visible);
        practisSetTabService().findPsLabelCounter(practisSet).shouldBe(exactText(count));
    }
}
