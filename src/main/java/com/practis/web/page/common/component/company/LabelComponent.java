package com.practis.web.page.common.component.company;

import static com.practis.utils.AwaitUtils.awaitSoft;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
public class LabelComponent {

  @FindBy(how = CSS, css = "div.sc-dYPeNj.bbWqaM")
  private WebElement labelsIcon;

  @FindBy(how = CSS, css = "div.sc-dYPeNj.bbWqaM")
  private WebElement backIcon;

  @FindBy(how = CSS, css = "div.sc-hBURRC.dJxSfg.sc-hRMXGO")
  private WebElement addLabelButton;

  @FindBy(how = CSS, css = "input.sc-iqsfdx.iYxixc.sc-gGPAug.fnAlKp")
  private WebElement labelNameInput;

  @FindBy(how = CSS, css = "div.sc-gXRoDt.iNINRs")
  private List<WebElement> labelElements;

  @FindBy(how = CSS, css = "a.sc-jRQAMF.cimCkU.sc-JkiRB.dMIDhj")
  private WebElement saveLabelButton;

  public LabelComponent openLabelsPanel() {
    labelsIcon.click();
    return this;
  }

  public LabelComponent closeLabelsPanel() {
    backIcon.click();
    return this;
  }

  /**
   * To be added.
   */
  public void addLabel(final String labelName) {
    openLabelsPanel();
    try {
      awaitSoft(3, () -> labelElements.size() > 0);
      if (labelElements.stream()
          .anyMatch(element -> element.getAttribute("title").equals(labelName))) {
        return;
      }

      addLabelButton.click();
      labelNameInput.sendKeys(labelName);
      saveLabelButton.click();
    } finally {
      closeLabelsPanel();
    }
  }
}
