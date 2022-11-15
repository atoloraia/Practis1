package com.practis.web.selenide.component.team;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class DuplicateTeamPopUp {
  private final SelenideElement duplicatesTitle = $(".sc-bMpsBf.eJgpvQ");
  private final SelenideElement progressTitle = $(".progress-title");
  private final SelenideElement progressbar = $(".progress.progress-striped.active");
  private final SelenideElement warningMessage = $(".sc-cbZaka.caJcla");
  private final SelenideElement stopButton = $(".sc-jcFkyM.fjErjd");
}
