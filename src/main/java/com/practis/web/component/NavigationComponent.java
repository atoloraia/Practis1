package com.practis.web.component;

import static com.practis.utils.SeleniumUtils.executeJs;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.web.page.library.LibraryPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@PractisPage
@Component("companyNavigationComponent")
public class NavigationComponent {

  @Autowired
  private WebDriver driver;

  @Autowired
  private LibraryPage libraryPage;

  @FindBy(how = CSS, css = "a.sc-eXlDFz.bGxczR")
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
  public LibraryPage goTo(final String name) {
    navigationItems.stream()
        .filter(element -> element.getText().trim().equals(name))
        .findFirst()
        .ifPresent(WebElement::click);
    return libraryPage;
  }
}
