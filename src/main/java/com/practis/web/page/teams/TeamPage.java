package com.practis.web.page.teams;

import static com.practis.utils.AwaitUtils.awaitElementHard;
import static com.practis.utils.AwaitUtils.awaitElementSoft;
import static com.practis.utils.AwaitUtils.awaitSeconds;
import static com.practis.utils.ExceptionUtils.exception;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.element.EmptyElement;
import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.practis.Team;
import com.practis.web.element.CompanySnackbar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@Slf4j
@RequiredArgsConstructor
public class TeamPage {

  private static final int TITLE_LOAD_TIMEOUT = 5;
  private static final int PAGE_LOAD_TIMEOUT = 30;
  private static final int COMPANY_SELECTOR_LOAD_TIMEOUT = 5;

  @Getter
  private final CompanySnackbar snackbar;

  @FindBy(how = CSS, css = "div.sc-fvprJM.jdfXwX")
  private WebElement title;

  @FindBy(how = CSS, css = "div.sc-hWBuvo.eeQeeT")
  private WebElement companySelector;

  @FindBy(how = CSS, css = "div.sc-hLVYkP.iBxYeA")
  private List<WebElement> companiesUnderSelector;

  @FindBy(how = CSS, css = "div.sc-kTqMCK.mjWEB")
  private WebElement adminCompany;

  @FindBy(how = CSS, css = "div.sc-iKMYjR.jOjoYA")
  private WebElement newEntitySelector;

  @FindBy(how = CSS, css = "a.sc-kJpAAQ.hnwDNE")
  private List<WebElement> newEntityItems;

  @FindBy(how = CSS, css = "tr.sc-bJcSFO.gazINW")
  private List<WebElement> teams;

  @FindBy(how = CSS, css = "div.sc-cvZATX.FrmZO")
  private List<WebElement> teamOptions;

  @FindBy(how = CSS, css = "div.sc-dYPeNj.bbWqaM")
  private WebElement labelsIcon;

  @FindBy(how = CSS, css = "div.sc-hBURRC.dJxSfg.sc-hRMXGO")
  private WebElement addLabelButton;

  @FindBy(how = CSS, css = "input.sc-iqsfdx.iYxixc.sc-gGPAug.fnAlKp")
  private WebElement labelNameInput;

  @FindBy(how = CSS, css = "a.sc-jRQAMF.cimCkU.sc-JkiRB.dMIDhj")
  private WebElement saveLabelButton;

  /**
   * To be added.
   */
  public boolean isOnPage() {
    return awaitElementSoft(TITLE_LOAD_TIMEOUT, title).getText() != null;
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public WebElement getSelectedCompany() {
    final Callable<WebElement> getSelectedCompanyCallable = () ->
        companySelector.findElement(cssSelector(".sc-kLntdL.eWHIsh"));

    awaitSeconds(PAGE_LOAD_TIMEOUT, () ->
        !(getSelectedCompanyCallable.call() instanceof EmptyElement)
            && !getSelectedCompanyCallable.call().getText().trim().equals(""));

    return getSelectedCompanyCallable.call();
  }

  /**
   * To be added.
   */
  public void selectAdmin() {
    awaitElementHard(PAGE_LOAD_TIMEOUT, companySelector).click();
    adminCompany.click();
  }

  /**
   * To be added.
   */
  public void selectCompany(final String automationCompanyName) {
    companySelector.click();

    awaitSeconds(COMPANY_SELECTOR_LOAD_TIMEOUT, () -> companiesUnderSelector.size() > 0);
    companiesUnderSelector.stream()
        .filter(company -> company.getText().startsWith(automationCompanyName))
        .findFirst()
        .ifPresent(WebElement::click);
  }

  /**
   * To be added.
   */
  public TeamPage openAddDropdown() {
    awaitElementHard(PAGE_LOAD_TIMEOUT, newEntitySelector).click();
    return this;
  }

  /**
   * To be added.
   */
  public WebElement findItemUnderAddDropdown(final String itemName) {
    return newEntityItems.stream()
        .filter(element -> itemName.contains(element.getText()))
        .findFirst().orElseThrow(exception(itemName + " row not found"));
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public List<Team> getTeams() {
    //wait until grid loaded
    Thread.sleep(3000);
    return teams.stream().map(this::getRowColumns)
        .map(fields -> Team.builder().name(fields.get(1).getText()).build())
        .filter(team -> !team.getName().equals("All Members"))
        .collect(Collectors.toList());
  }

  private List<WebElement> getRowColumns(final WebElement row) {
    return row.findElements(By.cssSelector(".sc-eQtFwO.fetRrW"));
  }

  /**
   * To be added.
   */
  public TeamPage openTeamOptions(final Team team) {
    teams.stream().map(this::getRowColumns)
        .filter(fields -> team.getName().equals(fields.get(1).getText()))
        .findFirst().orElseThrow()
        .get(7).click();
    return this;
  }

  /**
   * To be added.
   */
  public TeamPage select(final String labelName) {
    teamOptions.stream()
        .filter(element -> labelName.equals(element.getText()))
        .findFirst().orElseThrow()
        .click();
    return this;
  }

  public TeamPage clickLabelsIcon() {
    labelsIcon.click();
    return this;
  }

  /**
   * To be added.
   */
  public void addLabel(final String labelName) {
    addLabelButton.click();
    labelNameInput.sendKeys(labelName);
    saveLabelButton.click();
  }
}
