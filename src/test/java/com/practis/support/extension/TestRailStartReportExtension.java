package com.practis.support.extension;

import static com.practis.configuration.testrail.TestRailService.testRail;
import static com.practis.configuration.testrail.TestRailTestCaseHolder.setTestCasesFromTestRun;
import static com.practis.support.util.TestRailUtil.getAllCaseIds;
import static com.practis.utils.EnvironmentUtils.isRunOnContinuousIntegration;

import com.practis.configuration.testrail.TestRailCloseableResource;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

public class TestRailStartReportExtension implements BeforeAllCallback {

  private static final String TESTRAIL_REPORT = "TEST_RAIL";
  private static boolean STARTED = false;

  @Override
  public void beforeAll(final ExtensionContext context) {
    if (!isRunOnContinuousIntegration()) {
      return;
    }

    if (!STARTED) {
      testRail().tryToGetExisting().ifPresentOrElse(
          testRun -> testRail().storeTestRun(testRun),
          () -> testRail().createTestRun(getAllCaseIds()));
      setTestCasesFromTestRun();
      getStore(context).put(TESTRAIL_REPORT, new TestRailCloseableResource());
      STARTED = true;
    }
  }

  private Store getStore(ExtensionContext context) {
    return context.getRoot().getStore(Namespace.GLOBAL);
  }
}
