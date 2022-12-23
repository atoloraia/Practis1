package com.practis.web.selenide.component.team;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WarningRemoveFromTeamPopUp {

    private final SelenideElement warningTitle = $(".sc-fqmvxs.uhMJq");
    private final SelenideElement description = $(".sc-frjUtm.dWVufl");
    private final SelenideElement goBackButton = $(".sc-jcFkyM.gNfFbi.inverse");
    private final SelenideElement proceedButton = $(".sc-jcFkyM.gNfFbi.undefined.primary");
}
