package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewTeamInput;

public class TeamCreatePage {

  private final SelenideElement titleField = $(
      "input[data-test='team-name']");
  private final ElementsCollection userRows = $$("div[data-test='all-users-item']");
  private final SelenideElement addSelectedUsersLink = $("div[data-test='all-users-add-selected']");
  private final SelenideElement createButton = $("button[title='Create']");

  /**
   * Fill Create New Team form.
   */
  public void fillForm(final NewTeamInput inputData) {
    titleField.append(inputData.getName());

    userRows.get(0).$("input[type=checkbox]").parent().click();
    addSelectedUsersLink.click();
    createButton.click();
  }

}
