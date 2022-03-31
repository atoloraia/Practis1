package com.practis.web.page.company.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewChallengeInput;
import com.practis.dto.practis.Challenge;

@PractisPage
public class ChallengeEditAssertionPage extends ChallengeEditMappingPage {

  /**
   * To be added.
   */
  public void assertEqual(final NewChallengeInput input, final Challenge challenge) {
    assertEquals(input.getTitle(), challenge.getName());
    assertEquals(input.getDescription(), challenge.getDescription());
    assertEquals(input.getCustomerLines(), challenge.getCustomerLines());
  }
}
