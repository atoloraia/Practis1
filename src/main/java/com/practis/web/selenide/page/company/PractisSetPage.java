package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewPractisSetInput;

public class PractisSetPage {

  private final SelenideElement titleField = $("input.sc-iqsfdx.fmTFXi.sc-fKlbSM.hLDXKr");
  private final SelenideElement descriptionField = $("textarea.sc-ksdxAp.dxLFpd");
  private final SelenideElement publishButton = $("button.sc-AjmZR.beCdeY.undefined.primary");

  /**
   * Fill Title.
   */
  public void fillTitle(final NewPractisSetInput inputData) {
    titleField.append(inputData.getTitle());
  }

  public void fillForm(final NewPractisSetInput inputData, final String label) {
    fillTitle(inputData);
    descriptionField.append(inputData.getDescription());
  }
}
