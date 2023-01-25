package com.practis.web.selenide.validator.company.library.practisset;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetTabService;

import com.codeborne.selenide.CollectionCondition;

public class PractisSetTabValidator {

    /** Assert Action button on Practis Set Page. */
    public static void assertDisabledAssignLabelsButton() {
        practisSetTab().getAssignLabelsBulkAction().shouldBe(visible);
        practisSetTab().getAssignLabelsBulkAction().shouldBe(enabled);
    }

    /** Assert Label counter. */
    public static void assertLabelCountOnPsPage(final String practisSet, final String count) {
        practisSetTabService().findPsLabelCounter(practisSet).shouldBe(visible);
        practisSetTabService().findPsLabelCounter(practisSet).shouldBe(exactText(count));
    }

    /** Assert single action for the Practis Set. */
    public static void assertSingleActionPractisSetNoLabels() {
        practisSetTab().getEditSingleAction().shouldBe(visible);
        practisSetTab().getEditSingleAction().shouldBe(exactText("Edit"));
        practisSetTab().getAssignUsersSingleAction().shouldBe(visible);
        practisSetTab().getAssignUsersSingleAction().shouldBe(exactText("Assign Users"));
        practisSetTab().getDuplicateSingleAction().shouldBe(visible);
        practisSetTab().getDuplicateSingleAction().shouldBe(exactText("Duplicate"));
        practisSetTab().getArchiveSingleAction().shouldBe(visible);
        practisSetTab().getArchiveSingleAction().shouldBe(exactText("Archive"));
    }

    /** Assert single action for the Practis Set. */
    public static void assertSingleActionPractisSet() {
        practisSetTab().getEditSingleAction().shouldBe(visible);
        practisSetTab().getEditSingleAction().shouldBe(exactText("Edit"));
        practisSetTab().getAssignUsersSingleAction().shouldBe(visible);
        practisSetTab().getAssignUsersSingleAction().shouldBe(exactText("Assign Users"));
        practisSetTab().getAssignLabelsSingleAction().shouldBe(visible);
        practisSetTab().getAssignLabelsSingleAction().shouldBe(exactText("Assign Labels"));
        practisSetTab().getDuplicateSingleAction().shouldBe(visible);
        practisSetTab().getDuplicateSingleAction().shouldBe(exactText("Duplicate"));
        practisSetTab().getArchiveSingleAction().shouldBe(visible);
        practisSetTab().getArchiveSingleAction().shouldBe(exactText("Archive"));
    }

    /** Assert number of Practis Sets rows. */
    public static void assertPractisSetsRows(final Integer rows) {
        practisSetTab().getPractisSetRow().shouldBe(CollectionCondition.size(rows));
    }
}
