package com.practis.web.selenide.configuration.data.company;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.company.NewLabelInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewLabelInputData {

  public static NewLabelInput getNewLabelInput() {
    return getNewLabelInputs().get(0);
  }

  /**
   * Get all Lable input data.
   */
  public static List<NewLabelInput> getNewLabelInputs() {
    return Stream.of(loadConfig(
            "/configuration/web/input/company/label.json", NewLabelInput[].class))
        .collect(Collectors.toList());
  }

}
