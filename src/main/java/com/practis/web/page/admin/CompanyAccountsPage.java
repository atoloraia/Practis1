package com.practis.web.page.admin;

import static com.practis.utils.AwaitUtils.awaitElementHard;
import static com.practis.utils.AwaitUtils.awaitElementSoft;
import static com.practis.utils.AwaitUtils.awaitSeconds;
import static com.practis.utils.ExceptionUtils.exception;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.element.EmptyElement;
import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.admin.Company;
import com.practis.web.page.common.component.admin.NavigationComponent;
import com.practis.web.page.common.component.admin.SnackbarComponent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
public class CompanyAccountsPage {

  private static final int TITLE_LOAD_TIMEOUT = 5;
  private static final int PAGE_LOAD_TIMEOUT = 30;
  private static final int COMPANY_SELECTOR_LOAD_TIMEOUT = 20;

  @Getter
  private final SnackbarComponent snackbar;
  @Getter
  private final NavigationComponent navigation;

  @FindBy(how = CSS, css = "div.sc-hgKjYe.jKMUGV")
  private WebElement title;

  @FindBy(how = CSS, css = ".sc-gVkttX.iUPQZY")
  private WebElement newEntitySelector;

  @FindBy(how = CSS, css = ".sc-eXlDFz.hTKkUB")
  private List<WebElement> dropdownItems;

  @FindBy(how = CSS, css = "div.sc-fydGIT.cNOJdV")
  private WebElement companySelector;

  @FindBy(how = CSS, css = ".sc-bSqbRM.eeSYyN")
  private List<WebElement> companiesUnderSelector;

  @FindBy(how = CSS, css = "input.sc-iqsfdx.ebKlfV.sc-hCwKIl.dmBnOU")
  private WebElement searchField;

  @FindBy(how = CSS, css = "tr.sc-eoHYXO.geiMHT")
  private List<WebElement> companies;

  /**
   * To be added.
   */
  public boolean isOnPage() {
    return awaitElementSoft(TITLE_LOAD_TIMEOUT, title).getText() != null;
  }

  /**
   * To be added.
   */
  public CompanyAccountsPage newEntity() {
    awaitElementHard(PAGE_LOAD_TIMEOUT, newEntitySelector).click();
    return this;
  }

  /**
   * To be added.
   */
  public WebElement selectEntity(final String itemName) {
    return dropdownItems.stream().filter(element -> element.getText().equals(itemName)).findFirst()
        .orElseThrow(exception(itemName + " row not found"));
  }

  /**
   * To be added.
   */
  public CompanyAccountsPage clickCompanySelector() {
    awaitSeconds(PAGE_LOAD_TIMEOUT, () -> !(companySelector instanceof EmptyElement));
    companySelector.click();
    return this;
  }

  /**
   * To be added.
   */
  public Optional<WebElement> findCompanyUnderCompanySelector(final String companyName) {
    awaitSeconds(COMPANY_SELECTOR_LOAD_TIMEOUT, () -> companiesUnderSelector.size() > 0);
    return companiesUnderSelector.stream()
        .filter(company -> company.getText().startsWith(companyName)).findFirst();
  }

  public void search(final String input) {
    searchField.sendKeys(input);
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public List<Company> getCompanies() {
    //wait until grid loaded
    Thread.sleep(5000);
    return companies.stream().map(this::getRowColumns)
        .map(fields -> Company.builder()
            .name(getCompanyName(fields))
            .build())
        .collect(Collectors.toList());
  }

  /**
   * To be added.
   */
  public void goToCompany(final String name) {
    companies.stream()
        .filter(companyRow -> name.equals(getCompanyName(getRowColumns(companyRow))))
        .findFirst().orElseThrow()
        .click();
  }

  private String getCompanyName(final List<WebElement> fields) {
    return fields.get(0).findElement(cssSelector(".sc-fDMmKd.fPmZlv")).getText();
  }

  private List<WebElement> getRowColumns(final WebElement row) {
    return row.findElements(cssSelector(".sc-gDGGLu.beWvde"));
  }
}
