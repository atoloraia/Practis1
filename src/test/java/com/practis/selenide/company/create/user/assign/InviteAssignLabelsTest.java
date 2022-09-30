package com.practis.selenide.company.create.user.assign;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCleanLabelSearch;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelSearchingAfter1Char;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSearchElementsOnLabelsModal;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertTeamSearchingAfter1Char;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteAssignLabelsTest {

  private List<String> usersToRemove;
  private NewUserInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("User");
    inputData = getNewUserInput();
    inputData.setEmail(format(inputData.getEmail(), timestamp()));
    inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

    usersToRemove = new ArrayList<>();
    usersToRemove.add(inputData.getEmail());
  }


  /**
   * Invite User to the App: Assign: Labels section: Search.
   */
  @TestRailTest(caseId = 13315)
  @DisplayName("AssignLabels: Search")
  @LabelExtension(count = 2)
  void assignTeamsSearch(final List<RestCreateLabelResponse> labels) {
    Selenide.refresh();
    userService().addRow(inputData, "Admin",labels.get(0));
    userService().assignFirstUser();

    //assert search team
    assertSearchElementsOnLabelsModal();
    //assert clean search
    assertCleanLabelSearch(2);
    //Search should be performed after entering 1 character
    assertLabelSearchingAfter1Char(labels.get(0).getName());
    //assert empty state
    labelService().searchLabel("no results");
    assertNoLabelSearchResult();
  }


}
