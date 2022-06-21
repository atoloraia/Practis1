package com.practis.web.selenide.service.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelScenario;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;
import static com.practis.web.util.AwaitUtils.awaitElementCollectionSize;
import static com.practis.web.util.AwaitUtils.awaitElementEnabled;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static com.practis.web.util.SelenideSetDivUtilUtil.setDivText;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewScenarioInput;
import com.practis.web.selenide.component.GridRow;
import lombok.SneakyThrows;

public class ScenarioService {

  private static final int GENERATE_ALL_TIMEOUT = 10;

  /**
   * Fill Title.
   */
  public void fillTitle(final NewScenarioInput inputData) {
    scenarioCreatePage().getTitleField().append(inputData.getTitle());
  }

  /**
   * Fill Scenario Form.
   */
  @SneakyThrows
  public void fillForm(final NewScenarioInput inputData, final String label) {
    fillTitle(inputData);
    scenarioCreatePage().getDescriptionField().append(inputData.getDescription());

    scenarioCreatePage().getAddLabels().click();
    labelScenario().selectLabel(label).clickAddLabel();

    //Check snackbar message "Challenge published"
    snackbar().getMessage().shouldBe(exactText("labels have been assigned to Scenario"));

    scenarioCreatePage().getAddCustomerLine().click();
    setDivText(scenarioCreatePage().getCustomerField().get(0),
        inputData.getCustomerLine());

    scenarioCreatePage().getAddARepLine().click();
    setDivText(scenarioCreatePage().getRepField().get(0),
        inputData.getRepLine());
    awaitElementEnabled(10, () -> scenarioCreatePage().getGenerateForAllButton()).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, ()
        -> scenarioCreatePage().getPlayButtons(), 1);
  }

  /**
   * Fill Customer Line.
   */
  public void fillCustomerLine(final NewScenarioInput inputData) {
    scenarioCreatePage().getAddCustomerLine().click();
    setDivText(scenarioCreatePage().getCustomerField().get(0),
        inputData.getCustomerLine());
  }

  /**
   * Fill rep Line.
   */
  public void fillRepLine(final NewScenarioInput inputData) {
    scenarioCreatePage().getAddARepLine().click();
    setDivText(scenarioCreatePage().getRepField().get(0),
        inputData.getRepLine());
  }

  /**
   * Generate for all.
   */
  @SneakyThrows
  public void generateForAll() {
    Thread.sleep(1000);
    awaitElementEnabled(10, () -> scenarioCreatePage().getGenerateForAllButton()).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, ()
        -> scenarioCreatePage().getPlayButtons(), 1);
  }

  /**
   * Drag and drop lines.
   */
  public void moveLine(final int linePosition, final int moveLines) {
    final var draggableElement = scenarioCreatePage().getDraggableElements().get(linePosition);
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

  /**
   * Search scenario on grid by Scenario Title.
   */
  public GridRow searchScenario(final String name) {
    navigationCompanies().libraryNavigationItem.click();
    libraryTabs().goToTab("Scenarios");
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Click outside the scenario form and click Discard Changes.
   */
  public void exitScenarioWithDiscard() {
    jsClick(navigationCompanies().getProgressNavigationItem());
    discardChangeForm().discardChanges();
  }

  /**
   * Click outside the scenario form and click Save Changes.
   */
  public void exitScenarioeWithSave() {
    jsClick(navigationCompanies().getProgressNavigationItem());
    discardChangeForm().saveChanges();
  }

}
