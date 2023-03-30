package com.practis.web.selenide.component.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class DeactivateCompanyPopUp {

    private final SelenideElement deactivateCompanyTitle =
            $("div[data-test='dialog-wrapper-title']");
    private final SelenideElement deactivateCompanyDescription =
            $("ul[data-test='deactivation-info']");
    private final SelenideElement descriptionField = $("div[data-test='deactivation-input-info']");
    private final SelenideElement companyNameField = $("input[data-test='company-name-input']");

    private final SelenideElement cancelButton = $("button[data-test='dialog-wrapper-cancel']");
    private final SelenideElement confirmButton = $("button[data-test='dialog-wrapper-confirm']");

    public String getDescriptionText() {
        return getDeactivateCompanyDescription().text().replaceAll("\\n", "");
    }
}
