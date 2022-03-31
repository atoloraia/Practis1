package com.practis.web.page.company.practisset;

import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.LINK_TEXT;

import java.util.List;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

class PractisSetEditPage {

  @Autowired
  private AjaxElementLocatorFactory locatorFactory;

  @Autowired
  protected WebDriver driver;

  @FindBy(how = CSS, css = "input.sc-iqsfdx.fmTFXi.sc-jjjZmT.grClhP")
  protected WebElement titleFieldElement;

  @FindBy(how = CSS, css = "textarea.sc-ksdxAp.dxLFpd")
  protected WebElement descriptionFieldElement;

  @FindBy(how = CSS, css = "div.sc-dAEVOs.eUIynz")
  protected List<WebElement> scenarioRowElements;

  @FindBy(how = CSS, css = "div.sc-hSiqfj.iDBjwW")
  protected List<WebElement> challengeRowElements;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.iInfWM")
  protected WebElement publishButtonElement;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.hrHHUu")
  protected WebElement saveAsDraftButtonElement;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.dpIUpb.primary")
  protected WebElement confirmationButtonElement;

  @FindBy(how = CSS, css = "div.sc-gloWkm.kiyqdM")
  protected WebElement labelSelectorElement;

  @FindBy(how = CSS, css = "div.sc-kwDLmJ.bpQshy")
  protected List<WebElement> availableLabelElements;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.jxxlLg")
  protected WebElement saveLabelsButtonElement;

  @FindBy(how = CSS, css = "div.sc-ckxQbn")
  protected List<WebElement> selectTabElements;

  @FindBy(how = CSS, css = "div.sc-kVA-dmp.hztdkE")
  protected WebElement totalDurationElement;

  @FindBy(how = CSS, css = "div.sc-eylyhE.fwUGCQ")
  protected WebElement totalRepRequiredElement;

  @FindBy(how = CSS, css = "div.sc-cVA-DKn.dCbOBc")
  protected WebElement minimumAccuracyElement;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.dpIUpb.inverse")
  protected WebElement discardChangesButton;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.dpIUpb.primary")
  protected WebElement saveChangesButton;

  @PostConstruct
  public void init() {
    PageFactory.initElements(locatorFactory, this);
  }
}
