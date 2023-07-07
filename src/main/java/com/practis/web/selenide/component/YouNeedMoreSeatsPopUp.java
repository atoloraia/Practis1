package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class YouNeedMoreSeatsPopUp {

    public final SelenideElement youNeedMoreTitle = $("div[data-test='XXX']");
    public final SelenideElement exclamationMark = $("div[data-test='XXX']");
    public final SelenideElement crossButton = $("div[data-test='XXX']");
    public final SelenideElement youNeedMoreDescription = $("div[data-test='XXX']");
    public final SelenideElement manageUsersButton = $("button[data-test='XXX']");
    public final SelenideElement setALimitButton = $("button[data-test='XXX']");
}
