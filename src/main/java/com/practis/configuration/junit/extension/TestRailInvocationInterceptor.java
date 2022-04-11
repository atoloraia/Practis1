package com.practis.configuration.junit.extension;

import static com.practis.configuration.junit.LegalTestCaseHolder.canRunTestCase;
import static com.practis.configuration.junit.LegalTestCaseHolder.isCaseFilterDisabled;
import static java.lang.String.format;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;

import com.practis.configuration.testrail.TestRailTest;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestRailInvocationInterceptor implements ExecutionCondition {

  @Override
  public ConditionEvaluationResult evaluateExecutionCondition(final ExtensionContext context) {
    final var annotation = context.getTestMethod().orElseThrow()
        .getAnnotation(TestRailTest.class);
    if (isCaseFilterDisabled()) {
      return enabled("Local test execution");
    }
    if (canRunTestCase(annotation.caseId())) {
      return enabled("Legal to run");
    }
    return disabled(format("Test case %s not provided for run", annotation.caseId()));
  }
}
