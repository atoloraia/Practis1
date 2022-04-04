package com.practis.web.component;

import static com.practis.utils.AwaitUtils.awaitSoft;
import static java.lang.String.format;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@PractisPage
public class CompanySelectorComponent {

  @FindBy(how = CSS, css = "div[data-test='companyDropDownToggleButton']")
  private WebElement companySelector;

  @FindBy(how = CSS, css = "div[data-test*='company_']")
  private List<WebElement> companiesUnderSelector;

  CompanySelectorComponent(final AjaxElementLocatorFactory locatorFactory) {
    PageFactory.initElements(locatorFactory, this);
  }


  public String getSelected() {
    awaitSoft(10, () -> getCompanyName(companySelector.getText()).length() > 0);
    return getCompanyName(companySelector.getText());
  }

  /**
   * To be added.
   */
  public void selectCompany(final String companyName) {
    companySelector.click();
    awaitSoft(10, () -> findCompany(companyName).isPresent());
    findCompany(companyName).ifPresentOrElse(
        WebElement::click,
        () -> {
          throw new RuntimeException(format("Company %s not found in the list", companyName));
        });
  }

  private Optional<WebElement> findCompany(final String companyName) {
    return companiesUnderSelector.stream()
        .filter(company -> getCompanyName(company.getText()).equals(companyName))
        .findFirst();
  }

  private String getCompanyName(final String text) {
    if (Objects.isNull(text)) {
      return "";
    }
    if (text.contains("\n")) {
      return text.split("\n")[1];
    } else {
      return text;
    }
  }

}
