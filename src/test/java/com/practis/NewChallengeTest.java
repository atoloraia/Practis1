package com.practis;

import static com.practis.utils.StringUtils.currentDate;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.practis.dto.NewChallengeInput;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.support.TestRailTest;
import com.practis.web.WebApplication;
import com.practis.web.component.GridSearchComponent;
import com.practis.web.component.NavigationComponent;
import com.practis.web.component.NewItemComponent;
import com.practis.web.model.grid.ChallengeGridRow;
import com.practis.web.page.TeamsPage;
import com.practis.web.page.common.component.company.CampaignSnackbarComponent;
import com.practis.web.page.common.component.company.LabelComponent;
import com.practis.web.page.company.challenge.ChallengeEditAssertionPage;
import com.practis.web.page.company.library.tab.challenges.ChallengesGridMapping;
import com.practis.web.page.library.LibraryPage;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class NewChallengeTest {

  private final WebApplication webApplication;

  private final NewItemComponent newItemComponent;

  private final ChallengeEditAssertionPage newChallengePage;
  private final NavigationComponent navigationComponent;
  private final ChallengesGridMapping challengesGrid;
  private final CampaignSnackbarComponent snackbarComponent;
  private final GridSearchComponent searchComponent;

  private final LabelComponent labelComponent;

  private final TeamsPage teamsPage;
  private final LibraryPage libraryPage;

  private List<String> toRemove;

  @BeforeEach
  void init() {
    webApplication.initAutomationCompany();
    toRemove = new ArrayList<>();
  }

  /**
   * Create Challenge.
   */

  void publishChallenge() {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate())
        .description("description+" + currentDate())
        .customerLines(List.of("Hello! It is Challenge", "Hello! Great!"))
        .labels(List.of("Automated Test")).build();
    input.getLabels().forEach(labelComponent::addLabel);

    //when
    //Open Teams page. Click '+' button →  “Challenge”
    newItemComponent.clickNewItem().clickRow("Challenge");

    //Fill Challenge title, description, add Label, customer line, generate audio for all. Publish.
    newChallengePage.fillFormAndPublish(input);

    //Check snackbar message "Challenge published"
    assertEquals("Challenge published", snackbarComponent.getText());

    //Check that the scenario with appropriate data is shown in Library: Scenario
    navigationComponent.goTo("Library").goToTab("Challenges");
    searchComponent.search(input.getTitle());
    final var challenge = libraryPage.getGridComponent().getRows(ChallengeGridRow.class, 1).get(0);
    assertEquals(input.getTitle(), challenge.getName());

    //Open scenario and check the data
    challenge.getRowElement().click();
    newChallengePage.assertEqual(input, newChallengePage.getChallenge());
  }

  /**
   * Challenge: Save As Draft.
   */

  void saveAsDraftChallenge() {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate())
        .description("description+" + currentDate())
        .customerLines(List.of("Hello! It is Challenge", "Hello! Great!"))
        .labels(List.of("Automated Test")).build();
    input.getLabels().forEach(labelComponent::addLabel);

    //when
    //Open Teams page. Click '+' button →  “Challenge”
    newItemComponent.clickNewItem().clickRow("Challenge");

    //Fill Challenge title, description, add Label, customer line, generate audio for all.
    // Save as Draft
    newChallengePage.fillFormAndSaveAsDraft(input);

    //Check snackbar message "Challenge saved as draft"
    assertEquals("Challenge saved as Draft", snackbarComponent.getText());

    //Check that the scenario with appropriate data is shown in Library: Challenge.
    navigationComponent.goTo("Library").goToTab("Challenges");
    searchComponent.search(input.getTitle());
    final var challenge = challengesGrid.getFirstChallengeInGrid();
    assertEquals(input.getTitle(), challenge.getName());

    //Open scenario and check the data
    challengesGrid.goTo(challenge);
    newChallengePage.assertEqual(input, newChallengePage.getChallenge());
  }

  /**
   * Create Challenge: Discard Changes pop-up.
   */

  void discardChangesScenario() {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate()).build();

    //when
    //Click '+' button →  “Challenge”
    newItemComponent.clickNewItem().clickRow("Challenge");

    //Enter Title and click outside the modal. Click discard on “Discard changes?” pop up
    newChallengePage.fillForm(input);
    newChallengePage.clickOutOfForm().clickDiscardOnPopup();

    //Click '+' button →  “Challenge”. Enter Title and click outside the modal.
    // Click Save as Draft on “Discard changes?” pop up
    newItemComponent.clickNewItem().clickRow("Challenge");
    newChallengePage.fillForm(input);
    newChallengePage.clickOutOfForm().clickSaveOnPopup();

    //Check snackbar message "Challenge saved as draft"
    assertEquals("Challenge saved as Draft", snackbarComponent.getText());

    //Check that the scenario with appropriate data is shown in Library: Challenge
    navigationComponent.goTo("Library").goToTab("Challenges");
    searchComponent.search(input.getTitle());
    final var challenge = challengesGrid.getFirstChallengeInGrid();
    assertEquals(input.getTitle(), challenge.getName());

    //Open scenario and check the data
    challengesGrid.goTo(challenge);
    newChallengePage.assertEqual(input, newChallengePage.getChallenge());
  }

  /**
   * Create Challenge: Validation: Required fields.
   */

  void validationMessagesChallenge() {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate())
        .customerLines(List.of("Hello! It is Challenge", "Hello! Great!")).build();

    //when
    //Click '+' button →  “Challenge”. Publish
    newItemComponent.clickNewItem().clickRow("Challenge");
    newChallengePage.publish();

    //Check snackbar message “Title required“
    assertEquals("Title required", snackbarComponent.getErrorText());

    // Add Tittle and fill customer line
    newChallengePage.fillTitle(input.getTitle());
    newChallengePage.fillCustomerLines(List.of(input.getCustomerLines().get(0)), 1).publish();

    //Check snackbar message “Audio records required”
    assertEquals("Audio records required", snackbarComponent.getErrorText());

    //Generate Audio and add rep line. Publish
    newChallengePage.generateAllAudio()
        .fillCustomerLines(List.of(input.getCustomerLines().get(1)), 2).publish();
    ////Check snackbar message “Audio records required”
    assertEquals("Audio records required", snackbarComponent.getErrorText());

    //Generate Audio. Publish.
    newChallengePage.generateAllAudio().publish();

    //Check snackbar message "Challenge published"
    assertEquals("Challenge published", snackbarComponent.getText());

    //TODO Check that the scenario with appropriate data is shown in Library: Challenge.
    //TODO Open challenge and check the data.
  }

  /**
   * Create Challenge: CRUD for customer lines.
   */

  void crudCustomerRepLines() throws InterruptedException {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate())
        .customerLines(List.of("Hello! It is Challenge", "Hello! Great!")).build();

    //when
    //Click '+' button →  “Challenge”
    newItemComponent.clickNewItem().clickRow("Challenge");

    //Fill Challenge title, desctiption, add customer line.
    //Delete customer line → Click Cancel on confirmation pop-up.
    newChallengePage.fillTitle(input.getTitle()).fillDescription(input.getDescription())
        .fillCustomerLines(List.of(input.getCustomerLines().get(0)), 1).deleteCustomerLine(0)
        .discard();
    //Check the customer line is shown
    assertEquals(input.getCustomerLines().get(0),
        newChallengePage.getChallenge().getCustomerLines().get(0));

    //Delete customer line → Click Yes on confirmation pop-up.
    newChallengePage.deleteCustomerLine(0).save();
    //Check the customer line is not shown
    assertNull(newChallengePage.getChallenge().getCustomerLines());

    //Add two customer lines. Move the lines.
    newChallengePage.fillCustomerLines(input.getCustomerLines());
    newChallengePage.moveReplicaFieldDown(0);

    //Check customer lines position
    assertEquals(input.getCustomerLines().get(0),
        newChallengePage.getChallenge().getCustomerLines().get(1));
    assertEquals(input.getCustomerLines().get(1),
        newChallengePage.getChallenge().getCustomerLines().get(0));

    //Publish
    newChallengePage.generateAllAudio().publish();
    assertEquals("Challenge published", snackbarComponent.getText());
  }

  @AfterEach
  void cleanup() {
    toRemove.forEach(title -> practisApi().deleteChallenge(title));
  }
}
