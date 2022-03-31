package com.practis.web.page.admin.administrators;

import static org.openqa.selenium.support.How.CSS;

import com.practis.web.page.common.PractisBasePage;
import java.util.List;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

class AdministratorsGridPage extends PractisBasePage {

  @Autowired
  private AjaxElementLocatorFactory locatorFactory;

  @FindBy(how = CSS, css = "tr.sc-eoHYXO.geiMHT")
  protected List<WebElement> administratorsGridRowElements;

  @PostConstruct
  public void init() {
    PageFactory.initElements(locatorFactory, this);
  }

  protected String getGridRowColumnSelector() {
    return ".sc-gDGGLu.beWvde";
  }
}
