package com.practis.web.page.company.challenge;

import static com.practis.utils.AwaitUtils.awaitSoft;
import static com.practis.utils.SeleniumUtils.executeJs;
import static com.practis.utils.SeleniumUtils.jsClick;
import static com.practis.utils.SeleniumUtils.setDivText;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.openqa.selenium.By.xpath;

import com.practis.dto.NewChallengeInput;
import com.practis.web.element.CompanySnackbar;
import com.practis.web.page.common.component.company.LabelComponent;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;

public class ChallengeEditActionPage extends ChallengeEditPage {

  private static final int GENERATE_ALL_TIMEOUT = 10;

  @Autowired
  private LabelComponent labelComponent;

  @Autowired
  private CompanySnackbar snackbar;

  public void fillFormAndPublish(final NewChallengeInput input) {
    fillForm(input).generateAllAudio().publish();
  }

  public void fillFormAndSaveAsDraft(final NewChallengeInput input) {
    fillForm(input).generateAllAudio().saveAsDraft();
  }

  /**
   * To be added.
   */
  public ChallengeEditActionPage fillForm(final NewChallengeInput input) {
    fillTitle(input.getTitle());

    ofNullable(input.getLabels()).ifPresent(labels -> {
      labelSelectorElement.click();
      labels.stream()
          .map(this::getLabelElementOrThrow)
          .forEach(this::selectLabelElement);
      saveLabelsButtonElement.click();
    });

    fillDescription(input.getDescription());

    fillCustomerLines(input.getCustomerLines());
    return this;
  }

  /**
   * To be added.
   */
  public ChallengeEditActionPage fillCustomerLines(final List<String> inputLines) {
    ofNullable(inputLines).ifPresent(il -> fillCustomerLines(il, il.size()));
    return this;
  }

  /**
   * To be added.
   */
  public ChallengeEditActionPage fillCustomerLines(
      final List<String> inputLines, final int requiredSize) {
    ofNullable(inputLines).ifPresent(customerLines -> {
      setNumberOfCustomerLines(requiredSize);
      final var skipLines = requiredSize - inputLines.size();
      IntStream.range(0, requiredSize)
          .skip(skipLines)
          .forEach(idx -> setDivText(driver, replicaFieldElements.get(idx),
              customerLines.get(idx - skipLines)));
    });
    return this;
  }

  public ChallengeEditActionPage fillTitle(final String title) {
    ofNullable(title).ifPresent(titleFieldElement::sendKeys);
    return this;
  }

  public ChallengeEditActionPage fillDescription(final String description) {
    ofNullable(description).ifPresent(descriptionFieldElement::sendKeys);
    return this;
  }

  /**
   * To be added.
   */
  public ChallengeEditActionPage generateAllAudio() {
    generateForAllButtonElement.click();
    awaitSoft(GENERATE_ALL_TIMEOUT,
        () -> playButtonElements.size() == replicaFieldElements.size());
    return this;
  }

  public void publish() {
    awaitSoft(10, () -> isNull(snackbar.getText()));
    submitButtonElement.click();
  }

  public void saveAsDraft() {
    awaitSoft(10, () -> isNull(snackbar.getText()));
    saveAsDraftButtonElement.click();
  }

  public ChallengeEditActionPage clickOutOfForm() {
    executeJs(driver, "document.querySelectorAll('div.sc-QsvGH.cwaeNi')[0].click()");
    return this;
  }

  public void clickDiscardOnPopup() {
    popupDiscardButtonElement.click();
  }

  public void clickSaveOnPopup() {
    popupSaveButtonElement.click();
  }

  /**
   * To be added.
   */
  public ChallengeEditActionPage deleteCustomerLine(final int idx) {
    deleteCustomerLineButtons.get(idx).click();
    return this;
  }

  /**
   * To be added.
   */
  public ChallengeEditActionPage discard() {
    popupDiscardButtonElement.click();
    return this;
  }

  /**
   * To be added.
   */
  public ChallengeEditActionPage save() {
    popupSaveButtonElement.click();
    return this;
  }

  /**
   * To be added.
   */
  public ChallengeEditActionPage deleteAllCustomerLines() {
    deleteCustomerLineButtons.forEach(element -> {
      element.click();
      popupSaveButtonElement.click();
    });
    return this;
  }

  /**
   * To be added.
   */
  public void moveReplicaFieldDown(final int from) {
    final var element = draggableReplicaElements.get(from);
    new Actions(driver)
        .clickAndHold(element)
        .moveByOffset(0, -200)
        .release(element)
        .perform();
  }

  private void setNumberOfCustomerLines(final int size) {
    while (replicaFieldElements.size() != size) {
      addCustomerLineLinkElement.click();
      setNumberOfCustomerLines(size);
    }
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
