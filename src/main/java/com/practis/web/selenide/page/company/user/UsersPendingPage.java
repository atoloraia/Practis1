package com.practis.web.selenide.page.company.user;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersPendingPage {

  private final SelenideElement emptyStateIcon = $(".sc-hudVhu.mAhrj");

  private final SelenideElement emptyStateText = $(".sc-cxBQeN.hLaXyG");

}
