package com.practis.web.selenide.component.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class DeactivateCompanyPopUp {

    private final SelenideElement deactivateCompanyTitle =
            $("div[data-test='deactivation-company-modal-title']");
    private final ElementsCollection deactivateCompanyDescription =
            $$("li[data-test='deactivation-info-item']");
    private final SelenideElement descriptionField = $("div[data-test='deactivation-input-info']");
    private final SelenideElement companyNameField =
            $("input[data-test='deactivation-name-input']");

    private final SelenideElement crossButton =
            $("div[data-test='deactivation-company-modal-close']");
    private final SelenideElement confirmButton = $("button[data-test='deactivation-button']");

    public String getDescriptionText() {
        return getDeactivateCompanyDescription().get(0).text().replaceAll("\\n", "");
    }
}
