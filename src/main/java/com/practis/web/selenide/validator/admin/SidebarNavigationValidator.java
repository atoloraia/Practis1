package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SidebarNavigationValidator {

    /** Assert elements on Sidebar Navigation. */
    public static void assertElementsOnAdminSidebarNavigationMenu() {
        navigationAdmin().getCompanyNavigationItem().shouldBe(visible);
        navigationAdmin().getCompanyNavigationItem().shouldBe(enabled);
        navigationAdmin().getCompanyNavigationItem().shouldBe(exactText("Companies"));

        navigationAdmin().getAdminNavigationItem().shouldBe(visible);
        navigationAdmin().getAdminNavigationItem().shouldBe(enabled);
        navigationAdmin().getAdminNavigationItem().shouldBe(exactText("Administrators"));

        navigationAdmin().getLogsNavigationItem().shouldBe(visible);
        navigationAdmin().getLogsNavigationItem().shouldBe(enabled);
        navigationAdmin().getLogsNavigationItem().shouldBe(exactText("Event Log"));

        navigationAdmin().getAssessmentNavigationItem().shouldBe(visible);
        navigationAdmin().getAssessmentNavigationItem().shouldBe(enabled);
        navigationAdmin().getAssessmentNavigationItem().shouldBe(exactText("AI Assessment"));

        navigationAdmin().getManageUsersNavigationItem().shouldBe(visible);
        navigationAdmin().getManageUsersNavigationItem().shouldBe(enabled);
        navigationAdmin().getManageUsersNavigationItem().shouldBe(exactText("Manage Users"));
    }
}
