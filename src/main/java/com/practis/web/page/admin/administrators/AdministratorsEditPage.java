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

class AdministratorsEditPage extends PractisBasePage {

  @Autowired
  private AjaxElementLocatorFactory locatorFactory;

  @FindBy(how = CSS, css = "div.sc-hgKjYe.jKMUGV")
  protected WebElement headerNameElement;

  @FindBy(how = CSS, css = "div.sc-hAWCcR.kDoeSH")
  protected WebElement nameInfoElement;

  @FindBy(how = CSS, css = "div.sc-dMOKBa.jJDhIs")
  protected WebElement emailInfoElement;

  @FindBy(how = CSS, css = "input[name='firstName']")
  protected WebElement firstNameFieldElement;

  @FindBy(how = CSS, css = "input[name='lastName']")
  protected WebElement lastNameFieldElement;

  @FindBy(how = CSS, css = "input[name='email']")
  protected WebElement emailFieldElement;

  @PostConstruct
  public void init() {
    PageFactory.initElements(locatorFactory, this);
  }
}
