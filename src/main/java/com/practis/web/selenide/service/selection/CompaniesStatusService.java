package com.practis.web.selenide.service.selection;

import static com.codeborne.selenide.Condition.match;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companiesStatusModule;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.SelenideElement;

public class CompaniesStatusService {

    /** Find label checkbox. */
    public SelenideElement findStatusCheckbox(final String status) {
        final var statusRow =
                companiesStatusModule()
                        .getStatusRows()
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
        final var checkbox = statusRow.$("[data-test*='checkbox-view']");
        return checkbox;
    }

    /** Select Active Status. */
    public void selectActiveStatus() {
        jsClick(companiesStatusModule().getActiveStatus());
    }

    /** Select Inactive Status. */
    public void selectInactiveStatus() {
        jsClick(companiesStatusModule().getInactiveStatus());
    }

    /** Select Inactive Status. */
    public void selectOnlyInactiveStatusApply() {
        companiesStatusModule().getClearButton();
        jsClick(companiesStatusModule().getInactiveStatus());
        companiesStatusModule().getApplyButton().click();
    }

    /** Select Inactive + Active Status. */
    public void selectActiveInactiveStatusApply() {
        companiesStatusModule().getClearButton();
        jsClick(companiesStatusModule().getInactiveStatus());
        jsClick(companiesStatusModule().getActiveStatus());
        companiesStatusModule().getApplyButton().click();
    }
}
