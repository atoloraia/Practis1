package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NudgePopUp {

    private final SelenideElement nudgeTitle = $(".sc-iyuJA-D.cpdjxE");
    private final SelenideElement nudgeDescription = $(".sc-cvDkDw.eWKWcN");
    private final SelenideElement fromField = $(".sc-gKckTs.ewVxfk.sc-isIUIo.vyXTb");
    private final SelenideElement messageField = $(".sc-lbJCdB.cLJTOe");
    private final SelenideElement messageFieldText = $(".sc-hFxFgV.fPqRel.sc-bfjayW.kBNCCQ ");
    private final SelenideElement cancelButton = $(".sc-caiKgP.hztRxV.inverse");
    private final SelenideElement applyButton = $(".sc-caiKgP.hztRxV.undefined.primary");
    private final SelenideElement snackbarMessage = $("div[data-test='message-box']");
}
