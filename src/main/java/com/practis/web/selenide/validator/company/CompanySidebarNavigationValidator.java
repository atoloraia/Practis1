package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CompanySidebarNavigationValidator {

    /** Assert elements on Sidebar Navigation on Company level. */
    public static void assertElementsOnCompanySidebarNavigationMenu() {
        navigationCompany().getFeedNavigationItem().shouldBe(visible);
        navigationCompany().getFeedNavigationItem().shouldBe(enabled);
        navigationCompany().getFeedNavigationItem().shouldBe(exactText("Feed"));

        navigationCompany().getTeamsNavigationItem().shouldBe(visible);
        navigationCompany().getTeamsNavigationItem().shouldBe(enabled);
        navigationCompany().getTeamsNavigationItem().shouldBe(exactText("Teams"));

        navigationCompany().getAiAssessmentItem().shouldBe(visible);
        navigationCompany().getAiAssessmentItem().shouldBe(enabled);
        navigationCompany().getAiAssessmentItem().shouldBe(exactText("AI Assessment"));

        navigationCompany().getReportsItem().shouldBe(visible);
        navigationCompany().getReportsItem().shouldBe(enabled);
        navigationCompany().getReportsItem().shouldBe(exactText("Reports"));

        navigationCompany().getLibraryNavigationItem().shouldBe(visible);
        navigationCompany().getLibraryNavigationItem().shouldBe(enabled);
        navigationCompany().getLibraryNavigationItem().shouldBe(exactText("Library"));

        navigationCompany().getUsersNavigationItem().shouldBe(visible);
        navigationCompany().getUsersNavigationItem().shouldBe(enabled);
        navigationCompany().getUsersNavigationItem().shouldBe(exactText("Users"));
    }
}
