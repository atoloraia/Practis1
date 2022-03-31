package com.practis.web.page.scenario;

import static java.lang.Integer.valueOf;
import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.How.CSS;

import com.practis.dto.practis.Scenario;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

abstract class AbstractScenarioPage {

  @FindBy(how = CSS, css = "div.sc-faAiPf.hTYQhB")
  protected List<WebElement> scenarioTotals;

  @FindBy(how = CSS, css = "input.sc-iqsfdx.fmTFXi")
  protected WebElement titleField;

  @FindBy(how = CSS, css = "textarea.sc-ksdxAp.dxLFpd")
  protected WebElement descriptionField;

  @FindBy(how = CSS, css = "div[placeholder='Write customer’s line here']")
  @Getter
  protected List<WebElement> customerLines;

  @FindBy(how = CSS, css = "div[placeholder='Write representative’s line here']")
  @Getter
  protected List<WebElement> repLines;

  @FindBy(how = CSS, css = "button[title='Play']")
  protected List<WebElement> playButtons;

  /**
   * To be added.
   */
  public Scenario currentScenario() {
    return Scenario.builder()
        .title(titleField.getAttribute("value"))
        .description(descriptionField.getAttribute("value"))
        .customerLinesCount(valueOf(scenarioTotals.get(0).getText()))
        .repLinesCount(valueOf(scenarioTotals.get(1).getText()))
        .playButtonsCount(playButtons.size())
        .totalDuration(scenarioTotals.get(2).getText())
        .customerLines(customerLines.stream().map(WebElement::getText).collect(toList()))
        .repLines(repLines.stream().map(WebElement::getText).collect(toList()))
        .build();
  }
}
