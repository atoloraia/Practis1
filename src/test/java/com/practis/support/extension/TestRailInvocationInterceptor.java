package com.practis.support.extension;

import static com.practis.configuration.testrail.TestRailTestCaseHolder.canRunTestCase;
import static com.practis.utils.EnvironmentUtils.isRunOnContinuousIntegration;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;

import com.practis.support.TestRailTest;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestRailInvocationInterceptor implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(final ExtensionContext context) {
        if (!isRunOnContinuousIntegration() || context.getTestMethod().isEmpty()) {
            return enabled(
                    String.format("%s disabled", TestRailInvocationInterceptor.class.getName()));
        }

        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(TestRailTest.class);

        if (isNull(annotation)) {
            return disabled(
                    format(
                            "Test  %s is not marked with %s annotation",
                            context.getDisplayName(), TestRailTest.class.getName()));
        }
        if (canRunTestCase(annotation.caseId())) {
            return enabled("Legal to run");
        }

        return disabled(format("Test case %s not provided for run", annotation.caseId()));
    }
}
