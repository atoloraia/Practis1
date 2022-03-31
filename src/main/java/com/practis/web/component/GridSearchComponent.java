package com.practis.web.component;

import static com.practis.utils.SeleniumUtils.setAttribute;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@PractisPage
public class GridSearchComponent {

  private final WebDriver driver;

  @FindBy(how = CSS, css = "input[data-test='tableSearchInput']")
  protected WebElement searchFieldElement;

  GridSearchComponent(
      final AjaxElementLocatorFactory locatorFactory, final WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(locatorFactory, this);
  }

  public void search(final String input) {
    setAttribute(driver, searchFieldElement, "value", input);
  }
}
