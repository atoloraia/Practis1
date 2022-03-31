package com.practis.web.page.common.component.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@PractisPage
public class SnackbarComponent {

  @FindBy(how = CSS, css = ".sc-jrQzUz.kYiNmI")
  private WebElement successNotification;

  @FindBy(how = CSS, css = ".sc-gKckTs.gKvIyv")
  private WebElement errorNotification;

  SnackbarComponent(final AjaxElementLocatorFactory locatorFactory) {
    PageFactory.initElements(locatorFactory, this);
  }

  /**
   * test.
   */
  public String getSuccessNotification() {
    return successNotification.getText();
  }

  /**
   * test.
   */
  public String getErrorNotification() {
    return errorNotification.getText();
  }

  public void assertSuccessNotification(final String expected) {
    assertEquals(expected, getSuccessNotification());
  }

}
