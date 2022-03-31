package com.practis;

import static com.practis.utils.StringUtils.currentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.practis.dto.NewPractisSetInput;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.web.WebApplication;
import com.practis.web.model.grid.PractisSetGrid;
import com.practis.web.page.TeamsPage;
import com.practis.web.page.common.component.company.CampaignSnackbarComponent;
import com.practis.web.page.common.component.company.LabelComponent;
import com.practis.web.page.company.practisset.PractisSetEditAssertionPage;
import com.practis.web.page.library.LibraryPage;
import com.practis.web.page.practis.PractisSetNewPage;
import com.practis.web.page.practis.PractisSetQuickAssignmentPage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class NewPractisSetTest {

  private final WebApplication webApplication;

  private final PractisSetEditAssertionPage practisSetEditPage;
  private final LabelComponent labelComponent;
  private final CampaignSnackbarComponent snackbarComponent;

  private final TeamsPage teamsPage;
  private final LibraryPage libraryPage;

  private final PractisSetNewPage newPractisSetPage;
  private final PractisSetQuickAssignmentPage quickAssignmentPage;

  @BeforeEach
  void init() {
    webApplication.initAutomationCompany();
  }

  @PractisTest
  void publishPractisSet() {
    //given
    final var input = NewPractisSetInput.builder().title("Practis Set-" + currentDate())
        .description("Test Practis Set description").labels(List.of("Automated Test")).build();
    input.getLabels().forEach(labelComponent::addLabel);

    teamsPage.addNew("Practis Set");

    final var emptyPractisSet = practisSetEditPage.getPractisSet();
    practisSetEditPage.fillForm(input);

    final var filledPractisSet = practisSetEditPage.getPractisSet();

    assertNotEquals(emptyPractisSet.getTotalDuration(), filledPractisSet.getTotalDuration());
    assertNotEquals(emptyPractisSet.getTotalRepsRequired(),
        filledPractisSet.getTotalRepsRequired());

    practisSetEditPage.publish();
    //then
    assertEquals("Practis Set Published", snackbarComponent.getText());

    libraryPage.getAssignUsersComponent().clickCancel();

    libraryPage.getSearchComponent().search(input.getTitle());
    final var practisSetRow = libraryPage.getGridComponent().getRows(PractisSetGrid.class).get(0);
    assertEquals(input.getTitle(), practisSetRow.getTitle());

    libraryPage.getGridComponent().click(PractisSetGrid.class, practisSetRow);
    final var practisSet = practisSetEditPage.getPractisSet();
    assertEquals(filledPractisSet, practisSet);
  }

  @PractisTest
  void saveAsDraftPractisSet() {
    //given
    final var input = NewPractisSetInput.builder().title("Practis Set-" + currentDate())
        .description("Test Practis Set description").labels(List.of("Automated Test")).build();
    input.getLabels().forEach(labelComponent::addLabel);

    teamsPage.addNew("Practis Set");

    practisSetEditPage.fillForm(input);
    final var filledPractisSet = practisSetEditPage.getPractisSet();

    practisSetEditPage.saveAsDraft();
    //then
    assertEquals("Practis Set Saved as Draft", snackbarComponent.getText());

    teamsPage.getNavigation().goTo("Library");

    libraryPage.getSearchComponent().search(input.getTitle());
    final var practisSetRow = libraryPage.getGridComponent().getRows(PractisSetGrid.class).get(0);
    assertEquals(input.getTitle(), practisSetRow.getTitle());

    libraryPage.getGridComponent().click(PractisSetGrid.class, practisSetRow);
    final var practisSet = practisSetEditPage.getPractisSet();
    assertEquals(filledPractisSet, practisSet);
  }

  @PractisTest
  void discardChangesPractisSet() {
    //given
    final var input = NewPractisSetInput.builder()
        .title("Practis Set-" + currentDate())
        .build();

    teamsPage.addNew("Practis Set");

    practisSetEditPage.fillTitle(input.getTitle());

    practisSetEditPage.clickOutOfForm().discardChanges();

    teamsPage.addNew("Practis Set");
    practisSetEditPage.fillTitle(input.getTitle());
    final var filledPractisSet = practisSetEditPage.getPractisSet();

    practisSetEditPage.clickOutOfForm().saveChanges();

    //then
    assertEquals("Practis Set Saved as Draft", snackbarComponent.getText());

    teamsPage.getNavigation().goTo("Library");

    libraryPage.getSearchComponent().search(input.getTitle());
    final var practisSetRow = libraryPage.getGridComponent().getRows(PractisSetGrid.class).get(0);
    assertEquals(input.getTitle(), practisSetRow.getTitle());

    libraryPage.getGridComponent().click(PractisSetGrid.class, practisSetRow);
    final var practisSet = practisSetEditPage.getPractisSet();
    assertEquals(filledPractisSet, practisSet);
  }
}
