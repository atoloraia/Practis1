package com.practis.configuration.testrail;

import static com.practis.configuration.testrail.TestRailProperties.testRailConfig;
import static com.practis.utils.ConfigurationLoader.loadJson;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.CaseField;
import java.util.List;

public class TestRailService {

  private static final TestRail TEST_RAIL;

  private static final CaseField AUTOMATION_TYPE_FIELD;
  private static final CaseField MISSION_FIELD;
  private static final List<CaseField> CASE_FIELDS;


  static {
    final var properties = testRailConfig();
    TEST_RAIL = TestRail.builder(
        properties.getUrl(), properties.getUser(), properties.getPassword()).build();

    CASE_FIELDS = TEST_RAIL.caseFields().list().execute();
    MISSION_FIELD = CASE_FIELDS.stream()
        .filter(field -> field.getLabel().equals("Mission"))
        .findAny().orElseThrow();
    AUTOMATION_TYPE_FIELD = CASE_FIELDS.stream()
        .filter(field -> field.getLabel().equals("Automation Type"))
        .findAny().orElseThrow();
  }

  public static <T> T getTestRailInput(final int caseId, final Class<T> dataClass) {
    final var testCase = TEST_RAIL.cases().get(caseId, CASE_FIELDS).execute();
    return loadJson(testCase.getCustomField("mission"), dataClass);
  }
}
