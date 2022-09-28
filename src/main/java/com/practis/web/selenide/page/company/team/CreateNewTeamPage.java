package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CreateNewTeamPage {

  private final SelenideElement createNewTeamTitle = $("div[data-test='create-team-page-title']");
  private final SelenideElement titleField = $(
      "input[data-test='create-team-name']");

  private final SelenideElement createButton = $("button[data-test='create-team-save']");
  private final SelenideElement cancelButton = $("button[data-test='create-team-cancel']");
}
