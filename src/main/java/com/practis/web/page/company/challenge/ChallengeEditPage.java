package com.practis.web.page.company.challenge;

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

class ChallengeEditPage {

  @Autowired
  private AjaxElementLocatorFactory locatorFactory;

  @Autowired
  protected WebDriver driver;

  @FindBy(how = CSS, css = "input.sc-iqsfdx.fmTFXi.sc-gYvZud.iHvLQx")
  protected WebElement titleFieldElement;

  @FindBy(how = CSS, css = ".sc-ksdxAp.dxLFpd")
  protected WebElement descriptionFieldElement;

  @FindBy(how = CSS, css = "div.sc-cjVTEq")
  protected List<WebElement> replicaFieldElements;

  @FindBy(how = LINK_TEXT, linkText = "+ Add a customer line")
  protected WebElement addCustomerLineLinkElement;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.gitJLL")
  protected WebElement generateForAllButtonElement;

  @FindBy(how = CSS, css = "button[title='Play']")
  protected List<WebElement> playButtonElements;

  @FindBy(how = CSS, css = "div.sc-gyYGyc.eImNnc")
  protected WebElement labelSelectorElement;

  @FindBy(how = CSS, css = "div.sc-kwDLmJ.bpQshy")
  protected List<WebElement> availableLabelElements;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.iInfWM")
  protected WebElement submitButtonElement;
  @FindBy(how = CSS, css = "button.sc-bkkfTU.hrHHUu")
  protected WebElement saveAsDraftButtonElement;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.jxxlLg")
  protected WebElement saveLabelsButtonElement;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.dpIUpb.inverse")
  protected WebElement popupDiscardButtonElement;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.dpIUpb.primary")
  protected WebElement popupSaveButtonElement;

  @FindBy(how = CSS, css = "div.sc-bYwdaj.erzGAO")
  protected List<WebElement> deleteCustomerLineButtons;

  @FindBy(how = CSS, css = ".sc-iTTZDz.Zqlp")
  protected List<WebElement> draggableReplicaElements;

  @PostConstruct
  public void init() {
    PageFactory.initElements(locatorFactory, this);
  }
}
