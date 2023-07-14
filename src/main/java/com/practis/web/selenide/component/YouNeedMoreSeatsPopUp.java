package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class YouNeedMoreSeatsPopUp {

    public final SelenideElement youNeedMoreSeatsTitle = $(".sc-kPFmWG.crfcTO");
    public final SelenideElement crossButton = $(".sc-kwDLmJ.friYUN");
    public final SelenideElement youNeedMoreSeatsDescription = $(".sc-bzvcsD.jgiOns");
    public final SelenideElement quickTipDescription = $(".sc-jSwdEC.ijjaIy");

    public final SelenideElement manageInvitationsButton = $(".sc-efQUeY.bzaUTD.inverse");
    public final SelenideElement requestLimitChangeButton = $(".sc-efQUeY.bzaUTD.primary");
}
