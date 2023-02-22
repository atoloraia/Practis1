package com.practis.web.selenide.validator.company.users;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersDraftTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertEmptyPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyState;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersPage;
import static com.practis.web.selenide.validator.selection.CreatedBySectionValidation.assertElementsOnCreatedBySection;
import static com.practis.web.selenide.validator.selection.FilterValidator.assertFiltersElementsDefaultState;

public class DraftsTabValidator {

    /** Assert Users - Empty Drafts list. */
    public static void assertEmptyDraftsPage() {
        assertEmptyPage();
        usersDraftTab().getDraftColumn().shouldBe(visible);
        usersDraftTab().getDraftColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getDraftColumn().shouldBe(exactText("Drafts"));
        usersDraftTab().getUsersColumn().shouldBe(visible);
        usersDraftTab().getUsersColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getUsersColumn().shouldBe(exactText("Users"));
        usersDraftTab().getCreatedByColumn().shouldBe(visible);
        usersDraftTab().getCreatedByColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getCreatedByColumn().shouldBe(exactText("Created by"));
        usersDraftTab().getCreatedOnColumn().shouldBe(visible);
        usersDraftTab().getCreatedOnColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getCreatedOnColumn().shouldBe(exactText("Created on"));
        usersDraftTab().getEditedByColumn().shouldBe(visible);
        usersDraftTab().getEditedByColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getEditedByColumn().shouldBe(exactText("Edited by"));
        usersDraftTab().getEditedOnColumn().shouldBe(visible);
        usersDraftTab().getEditedOnColumn().shouldHave(attribute("disabled"));
        usersDraftTab().getEditedOnColumn().shouldBe(exactText("Edited on"));
        assertUsersEmptyState("No Drafts Yet");
    }

    /** Assert Users - Drafts list. */
    public static void assertDraftsPage() {
        assertUsersPage();
        usersDraftTab().getDraftColumn().shouldBe(visible);
        usersDraftTab().getDraftColumn().shouldBe(exactText("Drafts"));
        usersDraftTab().getUsersColumn().shouldBe(visible);
        usersDraftTab().getUsersColumn().shouldBe(exactText("Users"));
        usersDraftTab().getCreatedByColumn().shouldBe(visible);
        usersDraftTab().getCreatedByColumn().shouldBe(exactText("Created by"));
        usersDraftTab().getCreatedOnColumn().shouldBe(visible);
        usersDraftTab().getCreatedOnColumn().shouldBe(exactText("Created on"));
        usersDraftTab().getEditedByColumn().shouldBe(visible);
        usersDraftTab().getEditedByColumn().shouldBe(exactText("Edited by"));
        usersDraftTab().getEditedOnColumn().shouldBe(visible);
        usersDraftTab().getEditedOnColumn().shouldBe(exactText("Edited on"));

        usersPage().getListValues().get(0).shouldBe(visible);
        usersPage().getListValues().get(0).shouldBe(attribute("width", "0.1"));
        usersPage().getListValues().get(1).shouldBe(visible);
        usersPage().getListValues().get(2).shouldBe(visible);
        usersPage().getListValues().get(3).shouldBe(visible);
        usersPage().getListValues().get(4).shouldBe(visible);
        usersPage().getListValues().get(5).shouldBe(visible);
    }

    /** Assert Users - Drafts, empty filters modal. */
    public static void assertElementsDraftsFilters() {
        assertElementsOnCreatedBySection();
        assertElementsOnCreatedBySection();
        assertFiltersElementsDefaultState();
    }
}
