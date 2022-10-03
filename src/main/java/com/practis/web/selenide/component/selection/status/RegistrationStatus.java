package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class RegistrationStatus {

  private final SelenideElement pendingRegStatus = $("div[data-test='pending-label']");
  private final SelenideElement activeStatus = $("div[data-test='active-label']");
}
