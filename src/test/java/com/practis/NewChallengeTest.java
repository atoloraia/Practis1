package com.practis;

import static com.practis.utils.StringUtils.currentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.practis.dto.NewChallengeInput;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
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
import java.util.List;
import lombok.RequiredArgsConstructor;
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

  @BeforeEach
  void init() {
    webApplication.initAutomationCompany();
  }

  @PractisTest
  void publishChallenge() {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate())
        .description("description+" + currentDate())
        .customerLines(List.of("Hello! It is Challenge", "Hello! Great!"))
        .labels(List.of("Automated Test")).build();
    input.getLabels().forEach(labelComponent::addLabel);

    //when
    teamsPage.addNew("Challenge");
    newItemComponent.clickNewItem().clickRow("Challenge");
    newChallengePage.fillFormAndPublish(input);

    //then
    assertEquals("Challenge published", snackbarComponent.getText());

    navigationComponent.goTo("Library").goToTab("Challenges");
    searchComponent.search(input.getTitle());
    final var challenge = libraryPage.getGridComponent().getRows(ChallengeGridRow.class).get(0);
    assertEquals(input.getTitle(), challenge.getName());

    challenge.getRowElement().click();
    newChallengePage.assertEqual(input, newChallengePage.getChallenge());
  }

  @PractisTest
  void saveAsDraftChallenge() {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate())
        .description("description+" + currentDate())
        .customerLines(List.of("Hello! It is Challenge", "Hello! Great!"))
        .labels(List.of("Automated Test")).build();
    input.getLabels().forEach(labelComponent::addLabel);

    //when
    newItemComponent.clickNewItem().clickRow("Challenge");
    newChallengePage.fillFormAndSaveAsDraft(input);

    //then
    assertEquals("Challenge saved as Draft", snackbarComponent.getText());

    navigationComponent.goTo("Library").goToTab("Challenges");
    searchComponent.search(input.getTitle());
    final var challenge = challengesGrid.getFirstChallengeInGrid();
    assertEquals(input.getTitle(), challenge.getName());

    challengesGrid.goTo(challenge);
    newChallengePage.assertEqual(input, newChallengePage.getChallenge());
  }

  @PractisTest
  void discardChangesScenario() {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate()).build();

    //when
    newItemComponent.clickNewItem().clickRow("Challenge");
    newChallengePage.fillForm(input);
    newChallengePage.clickOutOfForm().clickDiscardOnPopup();

    //nothing to assert

    newItemComponent.clickNewItem().clickRow("Challenge");
    newChallengePage.fillForm(input);
    newChallengePage.clickOutOfForm().clickSaveOnPopup();

    //then
    assertEquals("Challenge saved as Draft", snackbarComponent.getText());

    navigationComponent.goTo("Library").goToTab("Challenges");
    searchComponent.search(input.getTitle());
    final var challenge = challengesGrid.getFirstChallengeInGrid();
    assertEquals(input.getTitle(), challenge.getName());

    challengesGrid.goTo(challenge);
    newChallengePage.assertEqual(input, newChallengePage.getChallenge());
  }

  @PractisTest
  void validationMessagesChallenge() {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate())
        .customerLines(List.of("Hello! It is Challenge", "Hello! Great!")).build();

    //when
    newItemComponent.clickNewItem().clickRow("Challenge");
    newChallengePage.publish();
    assertEquals("Title required", snackbarComponent.getErrorText());

    newChallengePage.fillTitle(input.getTitle());
    newChallengePage.fillCustomerLines(List.of(input.getCustomerLines().get(0)), 1).publish();
    assertEquals("Audio records required", snackbarComponent.getErrorText());

    newChallengePage.generateAllAudio()
        .fillCustomerLines(List.of(input.getCustomerLines().get(1)), 2).publish();
    assertEquals("Audio records required", snackbarComponent.getErrorText());

    newChallengePage.generateAllAudio().publish();
    assertEquals("Challenge published", snackbarComponent.getText());
  }

  @PractisTest
  void crudCustomerRepLines() throws InterruptedException {
    //given
    final var input = NewChallengeInput.builder().title("Challenge - " + currentDate())
        .customerLines(List.of("Hello! It is Challenge", "Hello! Great!")).build();

    //when
    newItemComponent.clickNewItem().clickRow("Challenge");
    newChallengePage.fillTitle(input.getTitle()).fillDescription(input.getDescription())
        .fillCustomerLines(List.of(input.getCustomerLines().get(0)), 1).deleteCustomerLine(0)
        .discard();

    assertEquals(input.getCustomerLines().get(0),
        newChallengePage.getChallenge().getCustomerLines().get(0));

    newChallengePage.deleteCustomerLine(0).save();
    assertNull(newChallengePage.getChallenge().getCustomerLines());

    newChallengePage.fillCustomerLines(input.getCustomerLines());
    newChallengePage.moveReplicaFieldDown(0);

    assertEquals(input.getCustomerLines().get(0),
        newChallengePage.getChallenge().getCustomerLines().get(1));
    assertEquals(input.getCustomerLines().get(1),
        newChallengePage.getChallenge().getCustomerLines().get(0));

    newChallengePage.generateAllAudio().publish();
    assertEquals("Challenge published", snackbarComponent.getText());
  }
}
