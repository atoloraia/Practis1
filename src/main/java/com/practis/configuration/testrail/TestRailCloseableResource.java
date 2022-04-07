package com.practis.configuration.testrail;

import static com.practis.configuration.testrail.TestRailReporter.reportResults;

import org.junit.jupiter.api.extension.ExtensionContext.Store.CloseableResource;

public class TestRailCloseableResource implements CloseableResource {

  @Override
  public void close() {
    reportResults();
  }
}
