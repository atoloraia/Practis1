package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class RegistrationStatus {
  private final SelenideElement regStatusTitle = $("span[data-test='registration-status']");
  private final SelenideElement pendingRegStatus = $("div[data-test='pending-label']");
  private final SelenideElement activeStatus = $("div[data-test='active-label']");
  private final SelenideElement pendingRegCheckbox = $("div[data-test='pending-view']");
  private final SelenideElement activeCheckbox = $("div[data-test='active-view']");
}
