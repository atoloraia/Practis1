package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.utils.AwaitUtils.awaitElementCollectionSize;
import static com.practis.utils.AwaitUtils.awaitElementEnabled;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelScenario;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.util.SelenideSetDivUtilUtil.setDivText;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewScenarioInput;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class ScenarioCreatePage {

  private final SelenideElement titleField = $("input[data-test='scenario-title']");
  private final SelenideElement descriptionField = $("textarea[data-test='scenario-description']");
  private final SelenideElement addLabels = $(".sc-jdXIPg.fWCwKV");

  private final SelenideElement generateForAllButton = $("button[title='Generate for All']");
  private final SelenideElement addCustomerLine = $("a[data-test='add-scenario-customer-line']");
  private final SelenideElement addARepLine = $("a[data-test='add-scenario-rep-line']");
  private final ElementsCollection customerField =
      $$("div[placeholder='Write customer’s line here']");
  private final ElementsCollection repField =
      $$("div[placeholder='Write representative’s line here']");
  private final ElementsCollection playButtons = $$("button[title='Play']");
  private final SelenideElement deleteLine = $("div[data-test='delete-scenario-customer-line']");

  private final SelenideElement publishButton = $("button[data-test='publish-scenario']");
  private final SelenideElement saveAsDraftButton = $("button[data-test='save-scenario-as-draft']");
  private final ElementsCollection draggableElements = $$(
      "div[data-rbd-drag-handle-draggable-id*='temp'");


  private static final int GENERATE_ALL_TIMEOUT = 10;


  /**
   * Fill Title.
   */
  public void fillTitle(final NewScenarioInput inputData) {
    titleField.append(inputData.getTitle());
  }

  /**
   * Fill Scenario Form.
   */
  @SneakyThrows
  public void fillForm(final NewScenarioInput inputData, final String label) {
    fillTitle(inputData);
    descriptionField.append(inputData.getDescription());

    addLabels.click();
    labelScenario().selectLabel(label).clickAddLabel();

    //Check snackbar message "Challenge published"
    snackbar().getMessage().shouldBe(exactText("labels have been assigned to Scenario"));

    addCustomerLine.click();
    setDivText(customerField.get(0),
        inputData.getCustomerLine());

    addARepLine.click();
    setDivText(repField.get(0),
        inputData.getRepLine());
    awaitElementEnabled(10, () -> generateForAllButton).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, () -> playButtons, 1);
  }

  /**
   * Fill Customer Line.
   */
  public void fillCustomerLine(final NewScenarioInput inputData) {
    addCustomerLine.click();
    setDivText(customerField.get(0),
        inputData.getCustomerLine());
  }

  /**
   * Fill Customer Line.
   */
  public void fillCustomerLine(final String customerLine) {
    addCustomerLine.click();
    setDivText(customerField.get(customerField.size() - 1), customerLine);
  }

  /**
   * Fill rep Line.
   */
  public void fillRepLine(final NewScenarioInput inputData) {
    addARepLine.click();
    setDivText(repField.get(0),
        inputData.getRepLine());
  }

  /**
   * Fill rep Line.
   */
  public void fillRepLine(final String repLine) {
    addARepLine.click();
    setDivText(repField.get(repField.size() - 1), repLine);
  }

  /**
   * Generate for all.
   */
  @SneakyThrows
  public void generateForAll() {
    Thread.sleep(1000);
    awaitElementEnabled(10, () -> generateForAllButton).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, () -> playButtons, 1);
  }


  /**
   * Drag and drop lines.
   */
  public void moveLine(final int linePosition, final int moveLines) {
    final var draggableElement = draggableElements.get(linePosition);
    final var lineHeight = draggableElement.parent().getRect().getHeight();
    final var yOffset = lineHeight * moveLines;

    draggableElement.parent().parent().scrollIntoView(false);
    Selenide.actions()
        .clickAndHold(draggableElement)
        .moveByOffset(0, yOffset)
        .moveByOffset(0, yOffset * 2)
        .release(draggableElement)
        .perform();
  }
}
