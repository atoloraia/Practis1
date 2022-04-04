package com.practis;

import static com.practis.utils.StringUtils.currentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.practis.dto.NewScenarioInput;
import com.practis.dto.practis.Scenario;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.web.WebApplication;
import com.practis.web.page.library.LibraryPage;
import com.practis.web.page.scenario.ScenarioNewPage;
import com.practis.web.page.scenario.ScenarioViewPage;
import com.practis.web.page.teams.TeamPage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class NewScenarioTest {

  private final WebApplication webApplication;

  private final TeamPage teamPage;
  private final ScenarioNewPage newScenarioPage;
  private final ScenarioViewPage scenarioViewPage;
  private final LibraryPage libraryPage;

  @BeforeEach
  void init() {
    webApplication.initAutomationCompany();
  }

  @PractisTest
  void publishScenario() {
    //given
    final var input = NewScenarioInput.builder()
        .title("Scenario-" + currentDate())
        .description("Test scenario description")
        .customerLine("Hello! How are you?")
        .repLine("Fine! Thank you!")
        .build();

    //Open Company. Click '+' button →  “Scenario”.
    teamPage.openAddDropdown().findItemUnderAddDropdown("Scenario").click();

    //fill form and generate audio
    newScenarioPage.fillForm(input).generateAudio().waitForGenerate(2);

    //assert Customer Lines, Rep Lines, Total Duration are updated.
    final var filledScenario = newScenarioPage.currentScenario();
    assertEquals(1, filledScenario.getCustomerLinesCount());
    assertEquals(1, filledScenario.getRepLinesCount());
    assertEquals("0m 3s", filledScenario.getTotalDuration());

    //publish scenario
    newScenarioPage.publish();

    //assert snackbar message "Scenario published".
    assertEquals("Scenario published", libraryPage.getSnackbar().getText());

    //assert created scenario is in library
    final var scenarios = libraryPage.getScenarios();
    final var scenarioInGrid = scenarios.stream()
        .filter(scenario -> scenario.getTitle().equals(input.getTitle()))
        .findFirst().orElse(null);
    assertNotNull(scenarioInGrid);

    //assert scenario data
    libraryPage.goToScenario(scenarioInGrid);
    final Scenario scenario = scenarioViewPage.currentScenario();

    assertEquals(input.getTitle(), scenario.getTitle());
    assertEquals(input.getDescription(), scenario.getDescription());
    assertEquals(1, scenario.getCustomerLinesCount());
    assertEquals(1, scenario.getRepLinesCount());
    assertEquals("0m 3s", scenario.getTotalDuration());
    assertEquals(input.getCustomerLine(), scenario.getCustomerLines().get(0));
    assertEquals(input.getRepLine(), scenario.getRepLines().get(0));
    assertEquals(2, scenario.getPlayButtonsCount());
  }

  @PractisTest
  void saveAsDraftScenario() {
    //given
    final var input = NewScenarioInput.builder()
        .title("Scenario-" + currentDate())
        .description("Test scenario description")
        .customerLine("Hello! How are you?")
        .repLine("Fine! Thank you!")
        .build();

    //Open Company. Click '+' button →  “Scenario”.
    teamPage.openAddDropdown().findItemUnderAddDropdown("Scenario").click();

    //fill form and generate audio
    newScenarioPage.fillForm(input).generateAudio().waitForGenerate(2);

    //save as scenario
    newScenarioPage.saveAsDraft();

    //assert snackbar message "Scenario saved as draft".
    assertEquals("Scenario saved as Draft", libraryPage.getSnackbar().getText());

    //TODO Check that the scenario with appropriate data is shown in Library: Scenario.

    //TODO Open scenario and check the data.
  }

  @PractisTest
  void discardChangesScenario() {
    //given
    final var input = NewScenarioInput.builder()
        .title("Scenario-" + currentDate())
        .build();

    //Open Company. Click '+' button →  “Scenario”.
    teamPage.openAddDropdown().findItemUnderAddDropdown("Scenario").click();

    //Enter Title and click outside the form and click "Discard"
    newScenarioPage.fillForm(input).clickOutOfForm().clickDiscardOnPopup();

    //TODO Not?
    assertFalse(libraryPage.getSnackbar().isNotificationAppears());

    //Open Company. Click '+' button →  “Scenario”.
    teamPage.openAddDropdown().findItemUnderAddDropdown("Scenario").click();

    //Enter Title and click outside the form and click "Save as Draft"
    newScenarioPage.fillForm(input).clickOutOfForm().clickSaveOnPopup();

    //assert notification
    assertEquals("Scenario saved as Draft", libraryPage.getSnackbar().getText());

    //assert created scenario is in library
    //todo go to library?
    System.out.printf("1");

    //TODO Open scenario and check the data.
  }

  @PractisTest
  void validationMessagesScenario() {
    //given
    final var input = NewScenarioInput.builder()
        .title("Scenario-" + currentDate())
        .description("Test scenario description")
        .customerLine("Hello! How are you?")
        .repLine("Fine! Thank you!")
        .build();

    //Open Company. Click '+' button →  “Scenario”.
    teamPage.openAddDropdown().findItemUnderAddDropdown("Scenario").click();

    //assert snackbar message “Title required“.
    newScenarioPage.publish();
    assertEquals("Title required", libraryPage.getSnackbar().getErrorText());

    //assert snackbar message “Scenario should have at least one line”.
    newScenarioPage.fillTitle(input).publish();
    assertEquals(
        "Scenario should have at least one line", libraryPage.getSnackbar().getErrorText());

    //assert “Audio records required” for customer line
    newScenarioPage.addCustomerLine().fillCustomerLine(input).publish();
    assertEquals(
        "Audio records required", libraryPage.getSnackbar().getErrorText());

    //assert “Audio records required” for rep line
    newScenarioPage.generateAudio().waitForGenerate(1).addRepLine().fillRepLine(input).publish();
    assertEquals(
        "Audio records required", libraryPage.getSnackbar().getErrorText());

    //assert snackbar message "Scenario published".
    newScenarioPage.generateAudio().waitForGenerate(2).publish();
    assertEquals(
        "Scenario published", libraryPage.getSnackbar().getText());

    //TODO Check that the scenario with appropriate data is shown  in Library: Scenario.
  }

  @PractisTest
  void crudCustomerRepLines() {
    //given
    final var input = NewScenarioInput.builder()
        .title("Scenario-" + currentDate())
        .description("Test scenario description")
        .customerLine("Hello! How are you?")
        .repLine("Fine! Thank you!")
        .build();

    //Open Company. Click '+' button →  “Scenario”.
    teamPage.openAddDropdown().findItemUnderAddDropdown("Scenario").click();

    //Fill Scenario Title, Desctiption. Click “Add a customer line” -> “Delete” → “Cancel”.
    newScenarioPage.fillTitle(input).fillDescription(input);
    newScenarioPage.addCustomerLine().deleteCustomerLine().clickDiscardOnPopup();
    assertEquals(1, newScenarioPage.getCustomerLines().size());

    //assert customer line deleted
    newScenarioPage.deleteCustomerLine().clickSaveOnPopup();
    assertEquals(0, newScenarioPage.getCustomerLines().size());

    ///Click “Add a rep line” -> “Delete” → “Cancel”.
    newScenarioPage.addRepLine().deleteRepLine().clickDiscardOnPopup();
    assertEquals(1, newScenarioPage.getRepLines().size());

    //assert rep line deleted
    newScenarioPage.deleteRepLine().clickSaveOnPopup();
    assertEquals(0, newScenarioPage.getRepLines().size());

    //assert scenario totals before input
    final var emptyScenario = newScenarioPage.currentScenario();
    assertEquals(0, emptyScenario.getCustomerLinesCount());
    assertEquals(0, emptyScenario.getRepLinesCount());
    assertEquals("0m 0s", emptyScenario.getTotalDuration());

    //TODO Move the lines.
  }
}
