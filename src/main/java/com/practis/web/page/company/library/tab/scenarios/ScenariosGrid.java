package com.practis.web.page.company.library.tab.scenarios;

import static org.openqa.selenium.support.How.CSS;

import java.util.List;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

class ScenariosGrid {

  @Autowired
  private AjaxElementLocatorFactory locatorFactory;

  @FindBy(how = CSS, css = "a.sc-cxBQeN.wwfay")
  private List<WebElement> gridRowElements;

  @PostConstruct
  public void init() {
    PageFactory.initElements(locatorFactory, this);
  }
}
