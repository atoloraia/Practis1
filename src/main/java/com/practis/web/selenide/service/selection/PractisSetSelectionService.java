package com.practis.web.selenide.service.selection;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.match;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.psModuleService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.service.company.InviteUserService;

public class PractisSetSelectionService {

  /**
   * Find team checkbox.
   */
  public SelenideElement findPractisSetCheckbox(final String practisSet) {
    final var practisSetRow =
        inviteUserPsModule().getPractisSetRows().find(Condition.matchText(practisSet));
    final var checkbox = practisSetRow.$("[data-test='practisset-item-checkbox']").sibling(0);
    return checkbox.parent();
  }

  /**
   * Find selected Practis Set checkbox.
   */
  public SelenideElement findSelectedPractisSetCheckbox(final String practisSet) {
    final var psRow =
        inviteUserPsModule().getPractisSetRows().find(match("attribute value", element -> {
          final var result = Selenide.$(element).text().split("\n")[0].trim();
          return result.equals(practisSet);
        }));
    return psRow.$("[data-test='practisset-item-checkbox-view']");
  }

  /**
   * Search Practis Set.
   */
  public void searchPs(final String input) {
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
  public void unSelectAllPs() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUserPsModule().getUnSelectedAllButton().click();
  }

  /**
   * Select Practis Set.
   */
  public InviteUserService selectPractisSet(final String practisSet) {
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    psModuleService().findPractisSetCheckbox(practisSet).click();
    return null;
  }

  /**
   * Hover Due Date icon.
   */
  public void hoverDueDateField() {
    inviteUserPsModule().getDueDateValue().get(0).hover();
  }


}
