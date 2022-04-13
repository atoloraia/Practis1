package com.practis;

import static com.practis.utils.StringUtils.currentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.practis.configuration.testrail.TestRailTest;
import com.practis.dto.NewTeamInput;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.web.WebApplication;
import com.practis.web.page.teams.TeamNewPage;
import com.practis.web.page.teams.TeamPage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class CreateTeamTest {

  private final WebApplication webApplication;

  private final TeamPage teamPage;
  private final TeamNewPage newTeamPage;

  @BeforeEach
  void init() {
    webApplication.initAutomationCompany();
  }

  /**
   * Create Team.
   */
  @TestRailTest(caseId = 64)
  @PractisTest
  void createTeam() {
    //given
    final var input = NewTeamInput.builder()
        .teamName("Team-" + currentDate())
        .build();

    //when
    teamPage.openAddDropdown().findItemUnderAddDropdown("Team").click();
    newTeamPage.fillForm(input).create();

    //then
    System.out.println(teamPage.getSnackbar());
    assertEquals("New team has been created", teamPage.getSnackbar().getText());
  }
}
