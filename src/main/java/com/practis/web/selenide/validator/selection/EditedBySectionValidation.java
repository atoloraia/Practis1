package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.editedByModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

public class EditedBySectionValidation {

    /** Assert elements on Created By model. */
    public static void assertElementsOnCreatedBySection() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        editedByModule().getEditedByTitle().shouldBe(visible);
        editedByModule().getEditedByTitle().shouldBe(exactText("Edited by"));
        editedByModule().getSearchField().shouldBe(visible);
        editedByModule().getSearchField().shouldBe(enabled);
        editedByModule().getSearchFieldIcon().shouldBe(visible);
        editedByModule().getCleanSearchIcon().shouldBe(hidden);
        editedByModule().getSelectedText().shouldBe(visible);
        editedByModule().getSelectedText().shouldBe(exactText("No Users selected"));
        editedByModule().getUnSelectedAllButton().shouldBe(visible);
        editedByModule().getUnSelectedAllButton().shouldBe(disabled);
        editedByModule().getNoUsersYetImage().shouldBe(enabled);
        editedByModule().getNoUsersYetImage().shouldBe(visible);
        editedByModule().getNoSearchResultText().shouldBe(visible);
        editedByModule().getNoSearchResultText().shouldBe(exactText("No Users Yet"));
    }
}
