package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LimitUserPopUp {

    public final SelenideElement limitUserPopUpTitle = $("div[data-test='limit-warning-title']");
    public final SelenideElement crossButton = $("div[data-test='limit-warning-close']");
    public final SelenideElement limitUserPopUpDescription =
            $("div[data-test='limit-warning-description']");
    public final SelenideElement limitCounter = $("div[data-test='existing-users-count']");
    public final SelenideElement quickTipIcon = $("span[data-test='quick-tip-icon']");
    public final SelenideElement quickTipTitle = $("b[data-test='quick-tip-title']");
    public final SelenideElement quickTipDescription = $("span[data-test='quick-tip-text']");

    public final SelenideElement manageInvitationsButton =
            $("button[data-test='manage-invitations-button']");
    public final SelenideElement requestLimitChangeButton =
            $("button[data-test='request-limit-change-button']");
}
