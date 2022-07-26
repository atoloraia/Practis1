package com.practis.web.selenide.page.company.user;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UserProfilePage {
  private final SelenideElement userName = $(".sc-jTkhVj.kffWlt");
  private final SelenideElement userEmail = $(".sc-fUvfzj.fdYgtz");
  private final SelenideElement pendingRegistrationLabel = $(".sc-kXZWOo.hoZrIy");
  private final SelenideElement assignButton = $(".sc-eWCvAn.oiBRu");


}
