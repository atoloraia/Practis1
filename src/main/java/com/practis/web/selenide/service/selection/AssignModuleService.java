package com.practis.web.selenide.service.selection;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersModule;

public class AssignModuleService {

    public void cancel() {
        assignUsersModule().getCancelButton().click();
    }

    public void apply() {
        assignUsersModule().getApplyButton().click();
    }

    public void applyLabel() {
        assignUsersModule().getApply().click();
    }
}
