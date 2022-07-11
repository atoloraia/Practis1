package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UserProfilePage {
  private final SelenideElement userName = $(".sc-RUXdv.bQkVRx");
  private final SelenideElement userEmail = $(".sc-jXjzBC,cFpbvX");
  private final SelenideElement pendingRegistrationLabel = $(".sc-funZWv.iZunay");


}
