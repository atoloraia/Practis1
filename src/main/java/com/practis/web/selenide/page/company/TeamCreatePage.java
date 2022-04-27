package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewTeamInput;

public class TeamCreatePage {

  private final SelenideElement titleField = $("input.sc-iqsfdx.fmTFXi.sc-fSAzZw.eaUyku");
  private final ElementsCollection userRows = $$("div.sc-gfXtND.iZzRJl.sc-hZfEQB.cnlRlI");
  private final SelenideElement addSelectedUsersLink = $("div.sc-iJzCWG.brTUmk");
  private final SelenideElement createButton = $("button.sc-bkkfTU.iInfWM.primary");

  /**
   * Test.
   */
  public void fillForm(final NewTeamInput inputData) {
    titleField.append(inputData.getName());

    userRows.get(0).$("input[type=checkbox]").parent().click();
    addSelectedUsersLink.click();
    createButton.click();
  }

}
