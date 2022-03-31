package com.practis.web.component;

import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@PractisPage
public class AssignUsersComponent {

  @FindBy(how = CSS, css = "button.sc-bkkfTU.eQTJfi.inverse")
  private WebElement cancelButton;

  AssignUsersComponent(final AjaxElementLocatorFactory locatorFactory) {
    PageFactory.initElements(locatorFactory, this);
  }

  public void clickCancel() {
    cancelButton.click();
  }
}
