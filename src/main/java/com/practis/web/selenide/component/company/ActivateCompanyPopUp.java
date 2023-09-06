package com.practis.web.selenide.component.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ActivateCompanyPopUp {

    private final SelenideElement activateCompanyTitle =
            $("div[data-test='activation-company-modal-title']");
    private final ElementsCollection activateCompanyDescription =
            $$("li[data-test='activation-info-item']");
    private final SelenideElement descriptionField = $("div[data-test='activation-input-info']");
    private final SelenideElement companyNameField = $("input[data-test='activation-name-input']");

    private final SelenideElement crossButton =
            $("div[data-test='activation-company-modal-close']");
    private final SelenideElement confirmButton = $("button[data-test='activation-button']");

    public String getDescriptionText() {
        return getActivateCompanyDescription().get(0).text().replaceAll("\\n", "");
    }
}
