package com.practis.web.selenide.service.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.psModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;

import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;
import java.util.List;

public class AssignPsAndDueDateService {

    public void clickSelectPractisSet(final List<NewPractisSetInput> practisSets) {
        usersService().clickUsersRegisteredSingleActionAssignPs();
        usersPage().getAssignPractisSetsAction().click();
        psModuleService().selectPractisSet(practisSets.get(0).getName());
        assignPractisSetsAndDueDatesModule().getApplyButton().click();
        assignPractisSetsAndDueDatesModule().getConfirmationSnackbarText().shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getConfirmationSnackbarText()
                .shouldBe(exactText("Changes have been saved"));
    }

    /** Assert grid row with input data. */
    public static void assertUserRow(final NewUserInput inputData, final GridRow gridRow) {
        gridRow.get("Users")
                .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
    }
}
