package com.practis.web.selenide.component.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class DeactivateCompanyPopUp {

    private final SelenideElement deactivateCompanyTitle =
            $("div[data-test='activate-company-title']");
    private final SelenideElement deactivateCompanyDescription =
            $("div[data-test='activate-company-description']");
    private final SelenideElement descriptionField = $("div[data-test='description-field']");
    private final SelenideElement companyNameField = $("div[data-test='company-name-field']");

    private final SelenideElement cancelButton = $("button[data-test='confirmation-modal-cancel']");
    private final SelenideElement confirmButton =
            $("button[data-test='confirmation-modal-confirm']");
}
