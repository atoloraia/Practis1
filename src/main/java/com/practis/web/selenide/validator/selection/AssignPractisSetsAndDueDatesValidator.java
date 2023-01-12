package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;

public class AssignPractisSetsAndDueDatesValidator {

    /** Assert empty state. */
    public static void assertEmptyAssignPractisSetsAndDueDatesModule() {
        assignPractisSetsAndDueDatesModule().getModuleTitle().shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getModuleTitle()
                .shouldBe(exactText("Assign Practis Sets and Due Dates"));

        assignPractisSetsAndDueDatesModule().getSearchField().shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getSearchField()
                .shouldBe(attribute("font-size", "13px"));
        assignPractisSetsAndDueDatesModule().getSearchField().shouldBe(attribute("type", "text"));
        assignPractisSetsAndDueDatesModule().getSearchFiledIcon().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().shouldBe(hidden);

        assignPractisSetsAndDueDatesModule().getEmptyStateIcon().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getEmptyStateText().shouldBe(visible);
        // assignPractisSetsAndDueDatesModule().getEmptyStateText()
        // .shouldBe(exactText("No Practis Sets yet"));

        assignPractisSetsAndDueDatesModule().getDividerLine().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getCancelButton().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getCancelButton().shouldBe(enabled);
        assignPractisSetsAndDueDatesModule().getCancelButton().shouldBe(exactText("Cancel"));
        assignPractisSetsAndDueDatesModule()
                .getCancelButton()
                .shouldBe(attribute("type", "submit"));
        assignPractisSetsAndDueDatesModule()
                .getCancelButton()
                .shouldBe(attribute("color", "default"));
        assignPractisSetsAndDueDatesModule()
                .getCancelButton()
                .shouldBe(attribute("width", "112px"));

        assignPractisSetsAndDueDatesModule().getApplyButton().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getApplyButton().shouldBe(exactText("Apply"));
        assignPractisSetsAndDueDatesModule()
                .getApplyButton()
                .shouldBe(attribute("color", "default"));
        assignPractisSetsAndDueDatesModule().getApplyButton().shouldBe(attribute("width", "112px"));
    }

    /** Assert empty state. */
    public static void assertAssignPractisSetsAndDueDatesModule() {
        assignPractisSetsAndDueDatesModule().getModuleTitle().shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getModuleTitle()
                .shouldBe(exactText("Assign Practis Sets and Due Dates"));

        assignPractisSetsAndDueDatesModule().getSearchField().shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getSearchField()
                .shouldBe(attribute("font-size", "13px"));
        assignPractisSetsAndDueDatesModule().getSearchField().shouldBe(attribute("type", "text"));
        assignPractisSetsAndDueDatesModule().getSearchFiledIcon().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().shouldBe(hidden);

        assignPractisSetsAndDueDatesModule().getSelectionCounter().shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getSelectionCounter()
                .shouldBe(exactText("No Practis Sets selected"));
        assignPractisSetsAndDueDatesModule().getSelectAllButton().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getSelectAllButton().shouldBe(exactText("Select All"));
        assignPractisSetsAndDueDatesModule()
                .getSelectAllButton()
                .shouldBe(attribute("color", "#4aa9e2"));
        assignPractisSetsAndDueDatesModule().getDueDatesTitle().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getDueDatesTitle().shouldBe(exactText("Due Dates"));

        assignPractisSetsAndDueDatesModule().getItemRow().get(0).shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getItemTitle().get(0).shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getItemCheckbox()
                .get(0)
                .shouldBe(attribute("type", "checkbox"));
        assignPractisSetsAndDueDatesModule().getItemCheckboxView().get(0).shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getItemCheckboxView()
                .get(0)
                .shouldBe(attribute("size", "12"));
        assignPractisSetsAndDueDatesModule().getDueDateContainer().get(0).shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getDueDateValue().get(0).shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getDueDateValue().get(0).shouldBe(exactText("—"));

        assignPractisSetsAndDueDatesModule().getEmptyStateIcon().shouldBe(hidden);
        assignPractisSetsAndDueDatesModule().getEmptyStateText().shouldBe(hidden);

        assignPractisSetsAndDueDatesModule().getDividerLine().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getCancelButton().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getCancelButton().shouldBe(enabled);
        assignPractisSetsAndDueDatesModule().getCancelButton().shouldBe(exactText("Cancel"));
        assignPractisSetsAndDueDatesModule()
                .getCancelButton()
                .shouldBe(attribute("type", "submit"));
        assignPractisSetsAndDueDatesModule()
                .getCancelButton()
                .shouldBe(attribute("color", "default"));
        assignPractisSetsAndDueDatesModule()
                .getCancelButton()
                .shouldBe(attribute("width", "112px"));

        assignPractisSetsAndDueDatesModule().getApplyButton().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getApplyButton().shouldBe(exactText("Apply"));
        assignPractisSetsAndDueDatesModule()
                .getApplyButton()
                .shouldBe(attribute("color", "default"));
        assignPractisSetsAndDueDatesModule().getApplyButton().shouldBe(attribute("width", "112px"));
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharAssignPsModule(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        assignPractisSetsAndDueDatesModule().getSearchField().append(String.valueOf(input));
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getItemRow().get(0).shouldBe(visible);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharAssignUsersModule(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        assignUsersAndDueDatesModule().getSearchField().append(String.valueOf(input));
        assignUsersAndDueDatesModule().getSearchFieldXButton().shouldBe(visible);
        assignUsersAndDueDatesModule().getUserRow().get(0).shouldBe(visible);
    }
}
