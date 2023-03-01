package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPSAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;

public class AssignPractisSetsAndDueDatesValidator {

    /** Assert empty state. */
    public static void assertAssignPsAndDueDateEmpty() {
        assertAssignPsAndDueDate();
        assignPSAndDueDatesModule().getEmptyStateIcon().shouldBe(visible);
        assignPSAndDueDatesModule().getEmptyStateText().shouldBe(visible);
        assignPSAndDueDatesModule().getEmptyStateText().shouldBe(exactText("No Practis Sets yet"));
    }

    /** Assert Assign PS and Due Dates module. */
    public static void assertAssignPsAndDueDate(String counter) {
        assignPSAndDueDatesModule().getSelectionCounter().shouldBe(visible);
        assignPSAndDueDatesModule().getSelectionCounter().shouldBe(exactText(counter));

        assignPSAndDueDatesModule().getItemRow().get(0).shouldBe(visible);
        assignPSAndDueDatesModule().getItemTitle().get(0).shouldBe(visible);
        assignPSAndDueDatesModule()
                .getItemCheckbox()
                .get(0)
                .shouldBe(attribute("type", "checkbox"));
        assignPSAndDueDatesModule().getItemCheckboxView().get(0).shouldBe(visible);
        assignPSAndDueDatesModule().getItemCheckboxView().get(0).shouldBe(attribute("size", "12"));
        assignPSAndDueDatesModule().getDueDateContainer().get(0).shouldBe(visible);
        assignPSAndDueDatesModule().getDueDateValue().get(0).shouldBe(visible);
        assignPSAndDueDatesModule().getDueDateValue().get(0).shouldBe(exactText("—"));
    }

    /** Assert Assign PS and Due Dates module. */
    public static void assertAssignPsAndDueDate() {
        assignPSAndDueDatesModule().getModuleTitle().shouldBe(visible);
        assignPSAndDueDatesModule()
                .getModuleTitle()
                .shouldBe(exactText("Assign Practis Sets and Due Dates"));

        assignPSAndDueDatesModule().getCancelButton().shouldBe(visible);
        assignPSAndDueDatesModule().getCancelButton().shouldBe(enabled);
        assignPSAndDueDatesModule().getCancelButton().shouldBe(exactText("Cancel"));
        assignPSAndDueDatesModule().getCancelButton().shouldBe(attribute("type", "submit"));
        assignPSAndDueDatesModule().getCancelButton().shouldBe(attribute("color", "default"));
        assignPSAndDueDatesModule().getCancelButton().shouldBe(attribute("width", "112px"));

        assignPSAndDueDatesModule().getApplyButton().shouldBe(visible);
        assignPSAndDueDatesModule().getApplyButton().shouldBe(exactText("Apply"));
        assignPSAndDueDatesModule().getApplyButton().shouldBe(attribute("color", "default"));
        assignPSAndDueDatesModule().getApplyButton().shouldBe(attribute("width", "112px"));
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharAssignPsModule(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        assignPSAndDueDatesModule().getSearchField().append(String.valueOf(input));
        assignPSAndDueDatesModule().getSearchClearIcon().shouldBe(visible);
        assignPSAndDueDatesModule().getItemRow().get(0).shouldBe(visible);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharAssignUsersModule(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        assignUsersAndDueDatesModule().getSearchField().append(String.valueOf(input));
        assignUsersAndDueDatesModule().getSearchFieldXButton().shouldBe(visible);
        assignUsersAndDueDatesModule().getUserRow().get(0).shouldBe(visible);
    }
}
