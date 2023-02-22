package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NudgePopUp {

    private final SelenideElement nudgeTitle = $(".sc-hKAaEb.eXXCNn");
    private final SelenideElement nudgeDescription = $(".sc-isIUIo.iULNQ");
    private final SelenideElement fromField = $(".sc-RHqyA.hvKjkh");
    private final SelenideElement fromFieldValue = $(".sc-gKckTs.ihIiix.sc-eGAcbj.fmpYjj");
    private final SelenideElement messageField = $(".sc-gnYPdP.kYNoPD");
    private final SelenideElement messageFieldText = $(".sc-cQYgEB.jNLVkP.sc-jcZeft.kxKaRd");
    private final SelenideElement cancelButton = $(".sc-caiKgP.hztRxV.inverse");
    private final SelenideElement applyButton = $(".sc-caiKgP.hztRxV.undefined.primary");
}
