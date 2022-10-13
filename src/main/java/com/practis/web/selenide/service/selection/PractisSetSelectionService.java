package com.practis.web.selenide.service.selection;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.service.company.InviteUserService;

public class PractisSetSelectionService {

  /**
   * Find team checkbox.
   */
  public SelenideElement findPractisSetCheckbox(final String practisSet) {
    final var practisSetRow = inviteUserPsModule().getPractisSetRows()
        .find(Condition.matchText(practisSet));
    final var checkbox = practisSetRow.$("[data-test='practisset-item-checkbox']").sibling(0);
    return checkbox.parent();
  }

  /**
   * Find selected Practis Set checkbox.
   */
  public SelenideElement findSelectedPractisSetCheckbox(final String practisSet) {
    final var practisSetRow = inviteUserPsModule().getPractisSetRows()
        .find(Condition.matchText(practisSet));
    return practisSetRow.$("[data-test='practisset-item-checkbox']");
  }

  /**
   * Search Practis Set.
   */
  public void searchPractisSet(final String input) {
    inviteUserPsModule().getSearchField().setValue(input.substring(0, input.length() - 1));
    inviteUserPsModule().getSearchField().append(input.substring(input.length() - 1));
  }

  /**
   * Select All Practis Sets.
   */
  public void selectAllPractisSets() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUserPsModule().getSelectedAllButton().get(1).click();
  }

  /**
   * Unselect All Team.
   */
  public void unSelectAllTeam() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamModule().getUnSelectedAllButton().click();
  }

  /**
   * Select Practis Set.
   */
  public InviteUserService selectPractisSet(final String practisSet) {
    await().pollDelay(ONE_SECOND).until(() -> true);
    practisSetModuleService().findPractisSetCheckbox(practisSet).click();
    return null;
  }


}
