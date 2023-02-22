package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class InvitingUsersPopUp {
    private final SelenideElement processingTitle = $(".sc-epgvaW.ejErFO");
    private final SelenideElement progressTitle = $(".progress-title");
    private final SelenideElement progressbar = $(".progress.progress-striped.active");
    private final SelenideElement warningMessage = $(".sc-cdqekL.bVLCcq");
    private final SelenideElement stopButton = $(".sc-caiKgP.loysy.undefined.primary");
}
