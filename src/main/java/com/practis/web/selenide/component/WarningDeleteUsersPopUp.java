package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WarningDeleteUsersPopUp {

    public final SelenideElement confirmActionTitle =
            $("div[data-test='confirmation-modal-title']");
    public final SelenideElement descriptionText =
            $("div[data-test='confirmation-modal-description']");
    public final SelenideElement goBackButton = $("button[data-test='confirmation-modal-cancel']");
    public final SelenideElement proceedButton =
            $("button[data-test='confirmation-modal-confirm']");
}
