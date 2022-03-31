package com.practis;

import static com.practis.utils.StringUtils.currentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.web.WebApplication;
import com.practis.web.page.teams.TeamPage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class CreateLabelTest {

  private final WebApplication webApplication;

  private final TeamPage teamPage;

  @BeforeEach
  void init() {
    webApplication.initAutomationCompany();
  }

  @PractisTest

  //add label
  void createLabel() {
    teamPage.clickLabelsIcon().addLabel("LabelName " + currentDate());

    //assert snackbar message “Label created”
    final var message = teamPage.getSnackbar().getText();
    assertEquals("Label created", message);
  }
}
