package com.practis.web.page.company.practisset;

import static java.lang.Integer.parseInt;

import com.practis.dto.practis.PractisSet;

public class PractisSetEditMappingPage extends PractisSetEditActionPage {

  /**
   * To be added.
   */
  public PractisSet getPractisSet() {
    return PractisSet.builder()
        .title(titleFieldElement.getAttribute("value"))
        .description(descriptionFieldElement.getAttribute("value"))
        .totalDuration(totalDurationElement.getText())
        .totalRepsRequired(parseInt(totalRepRequiredElement.getText()))
        .minimumAccuracy(minimumAccuracyElement.getText())
        .build();
  }
}
