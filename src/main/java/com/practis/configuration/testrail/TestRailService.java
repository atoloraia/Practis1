package com.practis.configuration.testrail;

import static com.practis.configuration.testrail.TestRailProperties.testRailConfig;
import static com.practis.dictionary.EnvironmentDictionary.TEST_RAIL_TEST_RUN;
import static com.practis.utils.StringUtils.currentDate;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.System.getenv;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.CaseField;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import com.codepine.api.testrail.model.Test;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
   * Gets test run id from env variable and tries to find it in TestRail.
   */
  public Optional<Run> tryToGetExisting() {
    return ofNullable(getenv(TEST_RAIL_TEST_RUN))
        .map(testIdString -> testRail().getExecutor()
            .runs().get(parseInt(testIdString)).execute());
  }

  /**
   * Creates new Test run in TestRail.
   */
  public void createTestRun(final List<Integer> caseIds) {
    final var project = getProject();
    final var run = testRail().getExecutor().runs()
        .add(project.getId(), new Run()
            .setName(format("Test automation %s", currentDate()))
            .setCaseIds(caseIds)
            .setIncludeAll(false)
        ).execute();
    storeTestRun(run);
  }

  /**
   * Check Test Run is not completed and store to memory.
   */
  public void storeTestRun(final Run testRun) {
    if (testRun.isCompleted()) {
      throw new RuntimeException(format("Test Run with id %s already completed", testRun.getId()));
    }
    this.testRun = testRun;
  }

  /**
   * Get all Test Cases under Test Run.
   */
  public Set<Integer> getTestCases() {
    return testRail().getExecutor().tests().list(testRun.getId()).execute().stream()
        .map(Test::getCaseId)
        .collect(Collectors.toSet());
  }

  /**
   * Adds result to test.
   */
  public void addResult(final Result testResult) {
    if (isNull(testRun)) {
      return;
    }
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
    return projects.get(parseInt(testRailConfig().getProject())).execute();
  }
}
