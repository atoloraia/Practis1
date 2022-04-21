package com.practis.web.selenide.configuration.data;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.NewCompanyInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewCompanyInputData {

  public static NewCompanyInput getNewCompanyInput() {
    return getNewCompanyInputs().get(0);
  }

  /**
   * Get all Company input data.
   */
  public static List<NewCompanyInput> getNewCompanyInputs() {
    return Stream.of(loadConfig("/configuration/web/input/company.json", NewCompanyInput[].class))
        .collect(Collectors.toList());
  }
}
