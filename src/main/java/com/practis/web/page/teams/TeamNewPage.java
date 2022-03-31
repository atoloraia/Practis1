package com.practis.web.page.teams;

import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewTeamInput;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
public class TeamNewPage {

  @FindBy(how = CSS, css = "input.sc-iqsfdx.fmTFXi.sc-fEyKBL.cwTwoO.custom-border")
  private WebElement title;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.iInfWM.primary")
  private WebElement createButton;

  public TeamNewPage fillForm(NewTeamInput input) {
    title.sendKeys(input.getTeamName());
    return this;
  }

  public void create() {
    createButton.click();

  }
}
