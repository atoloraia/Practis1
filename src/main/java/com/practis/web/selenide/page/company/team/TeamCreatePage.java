package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TeamCreatePage {

  private final SelenideElement createNewTeamTitle = $("div[data-test='create-team-page-title']");
  private final SelenideElement titleField = $(
      "input[data-test='team-name']");

  private final SelenideElement createButton = $("button[title='Create']");
  private final SelenideElement cancelButton = $("button[title='Cancel']");
}
