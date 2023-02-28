package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WarningDeletePopUp {
    private final SelenideElement warningTitle = $("div[data-test='confirmation-modal-title']");
    private final SelenideElement description =
            $("div[data-test='confirmation-modal-description']");
    private final SelenideElement goBackButton = $("button[data-test='confirmation-modal-cancel']");
    private final SelenideElement proceedButton =
            $("button[data-test='confirmation-modal-confirm']");
}
