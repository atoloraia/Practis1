package com.practis.web.selenide.service.company;

import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelPanel;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.practis.dto.NewLabelInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LabelPanelService {

    /** Create new Label. */
    public void createLabel(final NewLabelInput inputData) {
        openPanel();
        clickAddLabel();
        fillLabelInput(inputData.getName());
        saveLabel();
    }

    /** Open Label Panel. */
    public void openPanel() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        labelPanel().getExpandLabelIcon().click();
    }

    /** Click 'Add Label' button. */
    public void clickAddLabel() {
        labelPanel().getAddLabelLink().click();
    }

    /** Fill input field. */
    public void fillLabelInput(final String value) {
        labelPanel().getLabelInput().sendKeys(value);
    }

    /** Save input data. */
    public void saveLabel() {
        labelPanel().getSaveButton().click();
    }

    /** Get Label Name. */
    public void checkLabelExists(final String name) {
        awaitFullPageLoad(10);
        log.info("Looking for label: {}", name);
        labelPanel()
                .getLabelRow()
                .shouldHave(
                        CollectionCondition.anyMatch(
                                "labelName",
                                element -> {
                                    log.info(
                                            "Check label: '{}'",
                                            $(element).$("input").attr("value"));
                                    return name.equalsIgnoreCase(
                                            $(element).$("input").attr("value"));
                                }));
    }
}
