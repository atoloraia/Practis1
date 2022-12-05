package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersModule;

public class AssignUserModuleValidator {

    /** Assert disabled Apply button. */
    public static void assertDisabledApplyButton() {
        assignUsersModule().getApplyButton().shouldBe(visible);
        assignUsersModule().getApplyButton().shouldBe(disabled);
    }
}
