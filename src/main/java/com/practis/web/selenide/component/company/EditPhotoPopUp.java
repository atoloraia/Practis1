package com.practis.web.selenide.component.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class EditPhotoPopUp {

    private final SelenideElement editPhotoTitle = $(".sc-ibSMtA.jILzZG");
    private final SelenideElement zoomText = $(".sc-fBfrAH.jcsgvr");
    private final SelenideElement cancelButton = $(".sc-hmjpBu.kuijHN.inverse");
    private final SelenideElement saveButton = $(".sc-hmjpBu.kuijHN.primary");
}
