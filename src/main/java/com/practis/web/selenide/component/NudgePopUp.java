package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NudgePopUp {

    private final SelenideElement nudgeTitle = $(".sc-iUCjfc.kPDyBr");
    private final SelenideElement nudgeDescription = $(".sc-lajtyh.cYXBSK");
    private final SelenideElement fromField = $(".sc-bEvWmn.gQwfxn");
    private final SelenideElement fromFieldValue = $(".sc-gKckTs.hvepGZ.sc-kffHcM.haJspm");
    private final SelenideElement messageField = $(".sc-hvoLio.iTkWIg");
    private final SelenideElement messageFieldText = $(".sc-cCKzxu.ekNgNQ.sc-iOLwYm.iPJJac");
    private final SelenideElement cancelButton = $(".sc-efQUeY.bTWDpS.inverse");
    private final SelenideElement applyButton = $(".sc-efQUeY.bTWDpS.primary");
}
