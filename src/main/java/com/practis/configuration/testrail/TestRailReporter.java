package com.practis.configuration.testrail;

import static com.practis.configuration.testrail.TestRailProperties.testRailConfig;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.TestRail.Cases.Get;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestRailReporter {

  public static final TestRail TEST_RAIL;

  private static final List<Result> RESULTS = new ArrayList<>();

  static {
    final var properties = testRailConfig();
    TEST_RAIL = TestRail.builder(
        properties.getUrl(), properties.getUser(), properties.getPassword()).build();

  }

  public static void addResult(final Result result) {
    RESULTS.add(result);
  }

  /**
   * Reports results to TestRail.
   */
  public static void reportResults() {
    final var projects = TEST_RAIL.projects();
    Project project = projects.get(Integer.parseInt(testRailConfig().getProject())).execute();
    Run run = TEST_RAIL.runs()
        .add(project.getId(),
            new Run().setName("Test automation")
                .setIncludeAll(false)
                .setCaseIds(RESULTS.stream()
                    .map(Result::getCaseId).collect(Collectors.toList()))
        ).execute();
    List<ResultField> customResultFields = TEST_RAIL.resultFields().list().execute();
    TEST_RAIL.results().addForCases(run.getId(), RESULTS, customResultFields).execute();
    TEST_RAIL.runs().close(run.getId()).execute();
  }
}
