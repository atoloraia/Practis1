package com.practis.web.selenide.configuration.data.company;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewScenarioInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewScenarioInputData {

  public static NewScenarioInput getNewScenarioInput() {
    return getNewScenarioInputs().get(0);
  }

  /**
   * Get all Scenarious input data.
   */
  public static List<NewScenarioInput> getNewScenarioInputs() {
    return Stream.of(loadConfig(
            "/configuration/web/input/company/scenario.json", NewScenarioInput[].class))
        .collect(Collectors.toList());
  }

}
