package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.utils.AwaitUtils.awaitSoft;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewTeamInput;

public class TeamCreatePage {

  private final SelenideElement titleField = $("input.sc-iqsfdx.fmTFXi.sc-fSAzZw.eaUyku");
  private final ElementsCollection userRows = $$("div.sc-gfXtND.iZzRJl.sc-hZfEQB.cnlRlI");
  private final SelenideElement createButton = $("button.sc-bkkfTU.iInfWM.primary");

  /**
   * Test.
   */
  public void fillForm(final NewTeamInput inputData) {
    awaitSoft(5, () -> {
      System.out.println(userRows.size());
      return !userRows.isEmpty();
    });
    titleField.setValue(inputData.getName());
  }
}
