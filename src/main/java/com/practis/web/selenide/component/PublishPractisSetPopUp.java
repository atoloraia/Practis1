package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class PublishPractisSetPopUp {

    private final SelenideElement goBackButton = $("button[data-test='confirmation-modal-cancel']");
    private final SelenideElement publishButton =
            $("button[data-test='confirmation-modal-confirm']");

    public void goBack() {
        goBackButton.click();
    }

    public void publish() {
        publishButton.click();
    }
}
