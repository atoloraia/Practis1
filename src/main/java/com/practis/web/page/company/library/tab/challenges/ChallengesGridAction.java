package com.practis.web.page.company.library.tab.challenges;

import static com.practis.web.page.company.library.tab.challenges.mapper.ChallengeMapper.titleFromGridRow;
import static java.lang.String.format;

import com.practis.dto.practis.Challenge;

public class ChallengesGridAction extends ChallengesGrid {

  /**
   * To be added.
   */
  public void goTo(final Challenge challenge) {
    challengeGridRowElements.stream()
        .filter(row ->
            challenge.getName().equals(titleFromGridRow(row, getGridRowColumnSelector())))
        .findFirst().orElseThrow(() -> new RuntimeException(
            format("Grid row with title '%s' not found", challenge.getName()))
        ).click();
  }
}
