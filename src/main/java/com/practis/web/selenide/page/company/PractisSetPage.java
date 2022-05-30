package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.utils.AwaitUtils.awaitElementCollectionSize;
import static com.practis.utils.AwaitUtils.awaitElementEnabled;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelChallenge;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.util.SelenideSetDivUtilUtil.setDivText;
import static org.openqa.selenium.support.How.CSS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewScenarioInput;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
