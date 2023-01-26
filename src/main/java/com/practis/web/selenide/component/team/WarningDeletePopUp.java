package com.practis.web.selenide.component.team;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WarningDeletePopUp {
    private final SelenideElement warningTitle = $(".sc-ddDbQl.WYDyG");
    private final SelenideElement description = $(".sc-fEyKBL.jjRooQ");
    private final SelenideElement goBackButton = $(".sc-jcFkyM.gNfFbi.inverse");
    private final SelenideElement proceedButton = $(".sc-jcFkyM.gNfFbi.undefined.primary");
}
