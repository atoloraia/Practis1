package com.practis.web.selenide.service.selection;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.match;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.service.company.InviteUserService;

public class LabelSelectionService {

    /** Find label checkbox. */
    public SelenideElement findLabelCheckbox(final String label) {
        final var labelRow =
                labelModule()
                        .getLabelRows()
                        .find(
                                match(
                                        "child attribute value",
                                        element -> {
                                            final var input =
                                                    $(element)
                                                            .find(
                                                                    String.format(
                                                                            "input[value='%s']",
                                                                            label));
                                            return input.exists();
                                        }));
        final var checkbox = labelRow.$("[data-test='label-item-checkbox-view']");
        return checkbox;
    }

    /** Find selected label checkbox. */
    public SelenideElement findSelectedLabelCheckbox(final String label) {
        final var labelRow =
                labelModule()
                        .getLabelRows()
                        .find(
                                match(
                                        "attribute value",
                                        element -> {
                                            final var result =
                                                    Selenide.$(element)
                                                            .find(
                                                                    String.format(
                                                                            "input[value=%s]",
                                                                            label));
                                            return result.is(exist);
                                        }));
        return labelRow.$("[data-test='label-item-checkbox']");
    }

    /** Find selected label checkbox. */
    public SelenideElement findSelectedLabelCheckboxView(final String label) {
        final var labelRow =
                labelModule()
                        .getLabelRows()
                        .find(
                                match(
                                        "attribute value",
                                        element -> {
                                            final var result =
                                                    Selenide.$(element)
                                                            .find(
                                                                    String.format(
                                                                            "input[value=%s]",
                                                                            label));
                                            return result.is(exist);
                                        }));
        return labelRow.$("[data-test='label-item-checkbox-view']");
    }

    /** Search Label. */
    public void searchLabel(final String input) {
        labelModule().getSearchField().setValue(input.substring(0, input.length() - 1));
        labelModule().getSearchField().append(input.substring(input.length() - 1));
    }

    /** Select All Labels. */
    public void selectAllLabels() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        labelModule().getSelectedAllButton().click();
    }

    /** Unselect All Labels. */
    public void unSelectAllLabels() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        labelModule().getUnSelectedAllButton().click();
    }

    /** Select label. */
    public InviteUserService selectLabel(final String label) {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        labelModuleService().findLabelCheckbox(label).click();
        return null;
    }
}
