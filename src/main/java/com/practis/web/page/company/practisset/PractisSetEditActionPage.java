package com.practis.web.page.company.practisset;

import static com.practis.utils.AwaitUtils.awaitSoft;
import static com.practis.utils.SeleniumUtils.doubleClick;
import static com.practis.utils.SeleniumUtils.executeJs;
import static com.practis.utils.SeleniumUtils.jsClick;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.openqa.selenium.By.xpath;

import com.practis.dto.NewPractisSetInput;
import com.practis.web.page.common.component.company.CampaignSnackbarComponent;
import com.practis.web.page.scenario.ScenarioNewPage;
import java.util.Optional;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

public class PractisSetEditActionPage extends PractisSetEditPage {

  @Autowired
  private CampaignSnackbarComponent snackbar;

  public void fillTitle(final String title) {
    titleFieldElement.sendKeys(title);
  }

  /**
   * To be added.
   */
  public void fillForm(final NewPractisSetInput input) {
    ofNullable(input.getTitle()).ifPresent(this::fillTitle);
    descriptionFieldElement.sendKeys(input.getDescription());

    ofNullable(input.getLabels()).ifPresent(labels -> {
      labelSelectorElement.click();
      labels.stream()
          .map(this::getLabelElementOrThrow)
          .forEach(this::selectLabelElement);
      saveLabelsButtonElement.click();
    });

    addScenario();
    addChallenge();
  }

  private void addChallenge() {
    selectTabElements.stream()
        .filter(element -> element.getText().startsWith("Challenges"))
        .findFirst().orElseThrow()
        .click();

    awaitSoft(10, () -> challengeRowElements.size() > 0);
    challengeRowElements.stream().findFirst().ifPresent(element -> doubleClick(driver, element));
  }

  private void addScenario() {
    selectTabElements.stream()
        .filter(element -> element.getText().startsWith("Scenarios"))
        .findFirst().orElseThrow()
        .click();

    awaitSoft(10, () -> scenarioRowElements.size() > 0);
    scenarioRowElements.stream().findFirst().ifPresent(element -> doubleClick(driver, element));
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public void publish() {
    awaitSoft(10, () -> isNull(snackbar.getText()));
    publishButtonElement.click();
    confirmPublish();
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public void saveAsDraft() {
    awaitSoft(10, () -> isNull(snackbar.getText()));
    saveAsDraftButtonElement.click();
  }

  public PractisSetEditActionPage clickOutOfForm() {
    executeJs(driver, "document.querySelectorAll('div.sc-QsvGH.cwaeNi')[0].click()");
    return this;
  }

  public void discardChanges() {
    discardChangesButton.click();
  }

  public void saveChanges() {
    saveChangesButton.click();
  }

  private void confirmPublish() {
    awaitSoft(3, () -> !confirmationButtonElement.getTagName().equals("empty"));
    jsClick(driver, confirmationButtonElement);
  }

  private WebElement getLabelElementOrThrow(final String label) {
    awaitSoft(5, () -> getLabelElement(label).isPresent());
    return getLabelElement(label)
        .orElseThrow(() -> new RuntimeException(
            format("No label '%s' found. Please create labels first", label)));
  }

  private Optional<WebElement> getLabelElement(final String label) {
    return availableLabelElements.stream()
        .filter(labelElement -> labelElement.getText().equals(label))
        .findFirst();
  }

  private void selectLabelElement(final WebElement labelElement) {
    final var checkbox = labelElement.findElements(xpath(".//div[2]/label/input")).get(0);
    jsClick(driver, checkbox);
  }
}
