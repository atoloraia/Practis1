package com.practis.support.extension;

import static com.practis.configuration.testrail.TestRailService.testRail;
import static com.practis.utils.EnvironmentUtils.isRunOnContinuousIntegration;
import static java.lang.String.format;

import com.codepine.api.testrail.model.Result;
import com.practis.configuration.testrail.TestRailStatus;
import com.practis.support.TestRailTest;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestRailWatcherExtension implements TestWatcher {

  @Override
  public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
    addResult(extensionContext, TestRailStatus.UNTESTED);
  }

  @Override
  public void testSuccessful(ExtensionContext extensionContext) {
    addResult(extensionContext, TestRailStatus.PASSED);
  }

  @Override
  public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
    addResult(extensionContext, TestRailStatus.RETEST);
  }

  @Override
  public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
    addResult(extensionContext, TestRailStatus.FAILED);
  }

  private void addResult(ExtensionContext extensionContext, TestRailStatus status) {
    if (!isRunOnContinuousIntegration()) {
      return;
    }

    if (extensionContext.getElement().isPresent() && extensionContext.getElement()
        .get().isAnnotationPresent(TestRailTest.class)) {
      final var element = extensionContext.getElement().get().getAnnotation(TestRailTest.class);
      final var testResult = new Result()
          .setStatusId(status.getStatusId())
          .setComment(getComment(extensionContext))
          .setCaseId(element.caseId());
      testRail().addResult(testResult);
    }
  }

  private String getComment(final ExtensionContext context) {
    return context.getExecutionException()
        .map(throwable -> format(
            "%s: %s", throwable.getClass().getSimpleName(), throwable.getMessage()))
        .orElse("");
  }
}
