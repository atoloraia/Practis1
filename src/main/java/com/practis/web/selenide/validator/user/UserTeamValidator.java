package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserTeamModal;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

public class UserTeamValidator {

  /**
   * Assert no search results.
   */
  public static void assertNoTeamSearchResult() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUserTeamModal().getNoSearchResultText().shouldBe(visible);
    inviteUserTeamModal().getNoSearchResultImage().shouldBe(visible);
    inviteUserTeamModal().getSelectedText().shouldBe(visible);
    inviteUserTeamModal().getUnSelectedAllButton().shouldBe(visible);
    inviteUserTeamModal().getTeamRows().shouldBe(CollectionCondition.size(0));
  }

  /**
   * Assert search results.
   */
  public static void assertTeamSearchResult(final String team) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUserTeamModal().findTeamCheckbox(team).shouldBe(visible);
    inviteUserTeamModal().getTeamRows().shouldBe(CollectionCondition.size(1));
  }

  /**
   * Assert Select All.
   */
  public static void assertSelectAll() {
    inviteUserTeamModal().getTeamCheckbox().shouldBe(CollectionCondition.allMatch("checked",
        element -> Selenide.$(element).has(Condition.attribute("checked"))));
    inviteUserTeamModal().getSelectedText().shouldBe(matchText("Teams selected"));
  }

  /**
   * Assert Unselect All.
   */
  public static void assertUnSelectAll() {
    inviteUserTeamModal().getTeamCheckbox().should(CollectionCondition.allMatch("checked",
        element -> !Selenide.$(element).has(Condition.attribute("checked"))));
    inviteUserTeamModal().getSelectedText().shouldBe(exactText("No Teams selected"));
  }

}
