package com.practis.web.page.company.library.tab.challenges;

import static org.openqa.selenium.support.How.CSS;

import java.util.List;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

class ChallengesGrid {

  @Autowired
  private AjaxElementLocatorFactory locatorFactory;

  @FindBy(how = CSS, css = "tr.sc-bJcSFO.gazINW")
  protected List<WebElement> challengeGridRowElements;

  @FindBy(how = CSS, css = "tr.sc-jfBFna.hbTyeB")
  protected WebElement labelsGridRowElement;

  protected String getGridRowColumnSelector() {
    return ".sc-eQtFwO.fetRrW";
  }

  @PostConstruct
  public void init() {
    PageFactory.initElements(locatorFactory, this);
  }
}
