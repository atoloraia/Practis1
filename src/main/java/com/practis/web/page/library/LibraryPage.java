package com.practis.web.page.library;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.practis.Scenario;
import com.practis.web.component.AssignUsersComponent;
import com.practis.web.component.GridComponent;
import com.practis.web.component.GridSearchComponent;
import com.practis.web.element.CompanySnackbar;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
@Getter
public class LibraryPage {

  @Getter
  private final CompanySnackbar snackbar;
  @Getter
  private final AssignUsersComponent assignUsersComponent;
  @Getter
  private final GridSearchComponent searchComponent;
  @Getter
  private final GridComponent gridComponent;

  @FindBy(how = CSS, css = "tr.sc-bJcSFO.gazINW")
  private List<WebElement> scenarios;

  @FindBy(how = CSS, css = "a.sc-cxBQeN.wwfay")
  private List<WebElement> tabLinkElements;

  /**
   * To be added.
   */
  public void goToTab(final String tabName) {
    tabLinkElements.stream()
        .filter(link -> link.getText().equals(tabName))
        .forEach(WebElement::click);
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public List<Scenario> getScenarios() {
    //wait until grid loaded
    Thread.sleep(3000);
    return scenarios.stream().map(this::getRowColumns)
        .map(fields -> Scenario.builder()
            .title(fields.get(1).getText())
            .totalDuration(fields.get(2).getText())
            .build())
        .filter(team -> !team.getTitle().equals("All Members"))
        .collect(Collectors.toList());
  }

  private List<WebElement> getRowColumns(final WebElement row) {
    return row.findElements(cssSelector(".sc-eQtFwO.fetRrW"));
  }

  /**
   * To be added.
   */
  public void goToScenario(final Scenario scenario) {
    scenarios.stream()
        .filter(row -> scenario.getTitle().equals(getRowColumns(row).get(1).getText()))
        .findFirst().orElseThrow()
        .click();
  }
}
