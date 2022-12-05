package com.practis.configuration.testrail;

import org.junit.jupiter.api.extension.ExtensionContext.Store.CloseableResource;

public class TestRailCloseableResource implements CloseableResource {

    @Override
    public void close() {
        // testRail().closeTestRun();
    }
}
