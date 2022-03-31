package com.practis.web.page.common.component.admin;

import static com.practis.utils.SeleniumUtils.executeJs;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

@PractisPage
public class NavigationComponent {

  @Autowired
  private WebDriver driver;

  @FindBy(how = CSS, css = "a.sc-cNKpQo.cbzisw")
  private List<WebElement> navigationItems;

  NavigationComponent(final AjaxElementLocatorFactory locatorFactory) {
    PageFactory.initElements(locatorFactory, this);
  }

  public NavigationComponent makeActive() {
    executeJs(driver, "document.querySelectorAll(\"a[aria-current='page']\")[0].click()");
    return this;
  }

  /**
   * To be added.
   */
  public void goTo(final String name) {
    navigationItems.stream()
        .filter(element -> element.getText().trim().equals(name))
        .findFirst()
        .ifPresent(WebElement::click);
  }
}
