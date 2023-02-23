package com.practis.web.selenide.service.selection;

import static com.codeborne.selenide.Condition.matchText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.psModuleService;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;
import java.util.List;

public class AssignPsAndDueDateService {

    public void clickSelectPractisSet(final List<NewPractisSetInput> practisSets) {
        psModuleService().selectPractisSet(practisSets.get(0).getName());
        jsClick(assignPractisSetsAndDueDatesModule().getApplyButton());
    }

    /** Assert grid row with input data. */
    public static void assertUserRow(final NewUserInput inputData, final GridRow gridRow) {
        gridRow.get("Users")
                .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
    }
}
