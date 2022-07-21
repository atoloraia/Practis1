package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserTeamModal;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;

public class UserTeamValidator {

  /**
   * Assert no search results.
   */
  public static void assertNoTeamSearchResult() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUserTeamModal().getNoSearchResultText().shouldBe(visible);
    inviteUserTeamModal().getNoSearchResultImage().shouldBe(visible);
    inviteUserTeamModal().getNoSelectedText().shouldBe(visible);
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
}
