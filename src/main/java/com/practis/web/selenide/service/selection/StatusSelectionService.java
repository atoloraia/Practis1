package com.practis.web.selenide.service.selection;

import static com.codeborne.selenide.Condition.match;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryStatusModule;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.service.company.InviteUserService;

public class StatusSelectionService {

    /** Find label checkbox. */
    public SelenideElement findStatusCheckbox(final String status) {
        final var labelRow =
                libraryStatusModule()
                        .getStatusRow()
                        .find(
                                match(
                                        "child attribute value",
                                        element -> {
                                            final var input =
                                                    $(element)
                                                            .find(
                                                                    String.format(
                                                                            "div[text='%s']",
                                                                            status));
                                            return input.exists();
                                        }));
        final var checkbox = labelRow.$("[data-test*='checkbox-view']");
        return checkbox;
    }

    /** Select Archived Status. */
    public InviteUserService selectArchivedStatus() {
        jsClick(libraryStatusModule().getArchivedStatusCheckboxView());
        return null;
    }

    /** Select Draft Status. */
    public InviteUserService selectDraftStatus() {
        libraryStatusModule().getDraftStatusCheckboxView().click();
        return null;
    }
}
