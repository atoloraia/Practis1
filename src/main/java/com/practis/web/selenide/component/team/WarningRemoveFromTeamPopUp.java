package com.practis.web.selenide.component.team;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WarningRemoveFromTeamPopUp {

    private final SelenideElement warningTitle = $(".sc-dJtOMx.bHmqlV");
    private final SelenideElement description = $(".sc-dKrnIr.ftXqMD");
    private final SelenideElement goBackButton = $(".sc-efQUeY.emQwdx.inverse");
    private final SelenideElement proceedButton = $(".sc-efQUeY.emQwdx.primary");
}
