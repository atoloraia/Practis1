package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TeamMemberStatus {
  private final SelenideElement notStartedStatus = $("div[data-test='not-started-label']");
  private final SelenideElement notStartedCheckbox = $("div[data-test='not-started-view']");
  private final SelenideElement inProgressStatus = $("div[data-test='in-progress-label']");
  private final SelenideElement inProgressCheckbox = $("div[data-test='in-progress-view']");
  private final SelenideElement completedStatus = $("div[data-test='completed-label']");
  private final SelenideElement completedCheckbox = $("div[data-test='completed-view']");
}
