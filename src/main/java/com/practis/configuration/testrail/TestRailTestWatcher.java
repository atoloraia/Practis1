package com.practis.configuration.testrail;

import static java.lang.String.format;

import com.codepine.api.testrail.model.Result;
import java.util.Optional;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestRailTestWatcher implements TestWatcher, BeforeAllCallback {

  private static boolean STARTED = false;
  private static final String TESTRAIL_REPORT = "TEST_RAIL";

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    if (!STARTED) {
      getStore(extensionContext).put(TESTRAIL_REPORT, new TestRailCloseableResource());
      STARTED = true;
    }
  }


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

    if (extensionContext.getElement().isPresent() && extensionContext.getElement()
        .get().isAnnotationPresent(TestRailTest.class)) {
      final var element = extensionContext.getElement().get().getAnnotation(TestRailTest.class);
      Result result = new Result()
          .setStatusId(status.getStatusId())
          .setComment(getComment(extensionContext))
          .setCaseId(element.caseId());
      TestRailReporter.addResult(result);
    }
  }

  private Store getStore(ExtensionContext context) {
    return context.getRoot().getStore(Namespace.GLOBAL);
  }

  private String getComment(final ExtensionContext context) {
    return context.getExecutionException()
        .map(throwable -> format(
            "%s: %s", throwable.getClass().getSimpleName(), throwable.getMessage()))
        .orElse("");
  }
}
