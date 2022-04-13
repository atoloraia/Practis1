package com.practis.configuration.testrail;

import static com.practis.configuration.testrail.TestRailProperties.testRailConfig;
import static com.practis.utils.StringUtils.currentDate;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.CaseField;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import java.util.List;

public class TestRailService {

  private static TestRailService INSTANCE;
  private static int TEST_ID;

  private static final TestRail TEST_RAIL;
  private static final List<ResultField> RESULT_FIELDS;
  private static final List<CaseField> CASE_FIELDS;

  private Run testRun;

  static {
    final var properties = testRailConfig();
    TEST_RAIL = TestRail.builder(
        properties.getUrl(), properties.getUser(), properties.getPassword()).build();
    RESULT_FIELDS = TEST_RAIL.resultFields().list().execute();
    CASE_FIELDS = TEST_RAIL.caseFields().list().execute();
  }

  /**
   * Singleton.
   */
  public static TestRailService testRail() {
    if (isNull(INSTANCE)) {
      INSTANCE = new TestRailService();
    }
    return INSTANCE;
  }

  /**
   * Creates new Test run in TestRail.
   */
  public void createTestRun(final List<Integer> caseIds) {
    final var project = getProject();
    testRun = testRail().getExecutor().runs()
        .add(project.getId(), new Run()
            .setName(format("Test automation %s", currentDate()))
            .setCaseIds(caseIds)
            .setIncludeAll(false)
        ).execute();
  }

  /**
   * Adds result to test.
   */
  public void addResult(final Result testResult) {
    getExecutor().tests().list(testRun.getId()).execute().stream()
        .filter(test -> test.getCaseId() == testResult.getCaseId())
        .findFirst()
        .ifPresent(test ->
            getExecutor().results().add(test.getId(), testResult, RESULT_FIELDS).execute());
  }

  public void closeTestRun() {
    getExecutor().runs().close(testRun.getId()).execute();
  }

  private TestRail getExecutor() {
    return TEST_RAIL;
  }

  private Project getProject() {
    final var projects = getExecutor().projects();
    return projects.get(Integer.parseInt(testRailConfig().getProject())).execute();
  }
}
