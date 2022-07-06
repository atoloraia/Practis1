package com.practis.web.selenide.configuration.data.company;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.NewScenarioInput;
import com.practis.dto.NewUserInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewUserInputData {

  public static NewUserInput getNewUserInput() {
    return getNewUserInputs().get(0);
  }

  /**
   * Get all User input data.
   */
  public static List<NewUserInput> getNewUserInputs() {
    return Stream.of(loadConfig(
            "/configuration/web/input/company/user.json", NewUserInput[].class))
        .collect(Collectors.toList());
  }

}
