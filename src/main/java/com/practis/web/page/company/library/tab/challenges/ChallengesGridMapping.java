package com.practis.web.page.company.library.tab.challenges;

import static com.practis.utils.AwaitUtils.awaitSeconds;
import static com.practis.web.page.company.library.tab.challenges.mapper.ChallengeMapper.fromGridRow;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.practis.Challenge;
import java.util.List;
import java.util.stream.Collectors;

@PractisPage
public class ChallengesGridMapping extends ChallengesGridAction {

  public Challenge getFirstChallengeInGrid() {
    return getChallenges(1).get(0);
  }

  public List<Challenge> getChallenges() {
    return getChallenges(0);
  }

  /**
   * To be added.
   */
  public List<Challenge> getChallenges(final int expectedRowsNumber) {
    if (expectedRowsNumber > 0) {
      awaitSeconds(10, () -> challengeGridRowElements.size() == expectedRowsNumber);
    } else {
      awaitSeconds(3, () -> challengeGridRowElements.size() > 0);
    }
    return challengeGridRowElements.stream()
        .map(row -> fromGridRow(row, getGridRowColumnSelector(), labelsGridRowElement))
        .collect(Collectors.toList());
  }
}
