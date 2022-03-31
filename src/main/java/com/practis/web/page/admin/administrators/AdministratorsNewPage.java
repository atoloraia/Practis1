package com.practis.web.page.admin.administrators;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.How.CSS;

import com.practis.web.page.common.PractisBasePage;
import java.util.List;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

class AdministratorsNewPage extends PractisBasePage {

  @Autowired
  private AjaxElementLocatorFactory locatorFactory;

  @FindBy(how = CSS, css = "input[name*='email']")
  protected List<WebElement> emailFieldElements;

  @FindBy(how = CSS, css = "input[name*='firstName']")
  protected List<WebElement> firstNameFieldElements;

  @FindBy(how = CSS, css = "input[name*='lastName']")
  protected List<WebElement> lastNameFieldElements;

  @FindBy(how = CSS, css = "input[name*='password']")
  protected List<WebElement> passwordFieldElements;

  @FindBy(how = CSS, css = "button[type='submit']")
  protected WebElement createButtonElement;

  @FindBy(how = CSS, css = "div.sc-fcyzyh.jTsXuB")
  protected List<WebElement> inputRowElements;

  @FindBy(how = CSS, css = "a.sc-jRQAMF.cimCkU")
  protected WebElement addRowLinkElement;

  @FindBy(how = CSS, css = "div.sc-egiSv.cFnxLQ")
  protected List<WebElement> passwordVisibilityLinkElements;

  protected String getInputRowColumnSelector() {
    return ".sc-clArTI.cKzKhf";
  }

  protected String getErrorMessageSelector() {
    return ".sc-hxaKgE.jGPiQi";
  }

  @PostConstruct
  public void init() {
    PageFactory.initElements(locatorFactory, this);
  }
}
