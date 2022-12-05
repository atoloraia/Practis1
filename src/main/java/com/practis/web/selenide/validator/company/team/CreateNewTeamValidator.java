package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;

import com.codeborne.selenide.Condition;

public class CreateNewTeamValidator {

    /** Assert elements on Create New Team page. */
    public static void assertElementsEmptyCreateNewTeam() {
        teamCreatePage().getCreateNewTeamTitle().shouldBe(visible);
        teamCreatePage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Create New Team"));

        teamCreatePage().getTitleField().shouldBe(visible);
        teamCreatePage().getTitleField().shouldBe(attribute("maxlength", "50"));
        teamCreatePage().getTitleField().shouldBe(attribute("placeholder", "Team Name"));

        teamCreatePage().getCancelButton().shouldBe(visible);
        teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
        teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

        teamCreatePage().getCancelButton().shouldBe(visible);
        teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
        teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

        teamCreatePage().getCreateButton().shouldBe(visible);
        teamCreatePage().getCreateButton().shouldBe(disabled);
        teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
        teamCreatePage().getCancelButton().shouldBe(visible);
        teamCreatePage().getCreateButton().shouldBe(exactText("Create"));
    }

    /** Assert elements on Create New Team page: Already exists Name. */
    public static void assertElementsCreateNewTeamWithWarning() {
        teamCreatePage().getCreateNewTeamTitle().shouldBe(visible);
        teamCreatePage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Create New Team"));

        teamCreatePage().getTitleField().shouldBe(visible);
        teamCreatePage().getTitleField().shouldBe(attribute("maxlength", "50"));
        teamCreatePage().getTitleField().shouldBe(attribute("placeholder", "Team Name"));

        teamCreatePage().getCancelButton().shouldBe(visible);
        teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
        teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

        teamCreatePage().getCancelButton().shouldBe(visible);
        teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
        teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

        teamCreatePage().getCreateButton().shouldBe(visible);
        teamCreatePage().getCreateButton().shouldBe(enabled);
        teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
        teamCreatePage().getCancelButton().shouldBe(visible);
        teamCreatePage().getCreateButton().shouldBe(exactText("Create"));
    }

    /** Assert Create New Team has been closed. */
    public static void assertClosedCreateNewTeam() {
        teamCreatePage().getCreateNewTeamTitle().shouldBe(hidden);
        teamCreatePage().getCreateButton().shouldBe(hidden);
    }
}
