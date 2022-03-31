package com.practis.web.page.company.challenge;

import static com.practis.utils.AwaitUtils.awaitSoft;
import static java.util.Optional.of;

import com.practis.dto.practis.Challenge;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;

class ChallengeEditMappingPage extends ChallengeEditActionPage {

  public Challenge getChallenge() {
    awaitSoft(5, () -> replicaFieldElements.size() > 0);
    return Challenge.builder()
        .name(of(titleFieldElement.getAttribute("value"))
            .filter(val -> val.length() > 0).orElse(null))
        .description(of(descriptionFieldElement.getAttribute("value"))
            .filter(val -> val.length() > 0).orElse(null))
        .customerLines(of(replicaFieldElements.stream()
            .map(WebElement::getText)
            .filter(val -> val.length() > 0)
            .collect(Collectors.toList()))
            .filter(list -> list.size() > 0)
            .orElse(null))
        .build();
  }

}
