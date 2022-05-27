package com.practis;

import static com.practis.utils.StringUtils.currentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.practis.dto.NewTeamInput;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.support.TestRailTest;
import com.practis.web.WebApplication;
import com.practis.web.page.teams.TeamNewPage;
import com.practis.web.page.teams.TeamPage;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class CreateTeamTest {

  private final WebApplication webApplication;

  private final TeamPage teamPage;
  private final TeamNewPage newTeamPage;

  private List<String> teamsToRemove;

  @BeforeEach
  void init() {
    webApplication.initAutomationCompany();
    teamsToRemove = new ArrayList<>();
  }

  /**
   * Create Team.
   */

  void createTeam() {
    //given
    final var input = NewTeamInput.builder().name("Team-" + currentDate()).build();

    //when
    teamPage.openAddDropdown().findItemUnderAddDropdown("Team").click();
    teamsToRemove.add(input.getName());
    newTeamPage.fillForm(input).create();

    //then
    System.out.println(teamPage.getSnackbar());
    assertEquals("New team has been created", teamPage.getSnackbar().getText());
  }
}
