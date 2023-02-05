package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.createdByModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

public class CreatedBySectionValidation {

    /** Assert elements on Created By model. */
    public static void assertElementsOnCreatedBySection() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        createdByModule().getCreatedByTitle().shouldBe(visible);
        createdByModule().getCreatedByTitle().shouldBe(exactText("Created by"));
        createdByModule().getSearchField().shouldBe(visible);
        createdByModule().getSearchField().shouldBe(enabled);
        createdByModule().getSearchFieldIcon().shouldBe(visible);
        createdByModule().getCleanSearchIcon().shouldBe(hidden);
        createdByModule().getSelectedText().shouldBe(visible);
        createdByModule().getSelectedText().shouldBe(exactText("No Users selected"));
        createdByModule().getSelectedAllButton().shouldBe(visible);
        createdByModule().getSelectedAllButton().shouldBe(enabled);
        createdByModule().getUserCheckbox().get(0).shouldBe(enabled);
        createdByModule().getUserName().get(0).shouldBe(visible);
    }
}
