package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersDraftTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.saveAsDraftService;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;

public class DraftUsersService {

    /** Click on 3-dot menu for the Users - Draft - Edit. */
    public void clickSingleActionEdit() {
        usersPage().getThreeDotMenu().get(0).click();
        usersDraftTab().getEditAction().click();
    }

    /** Click on 3-dot menu for the Users - Draft - Delete Draft. */
    public void clickSingleActionDeleteDraft() {
        usersPage().getThreeDotMenu().get(0).click();
        usersDraftTab().getDeleteAction().click();
    }

    /** Click on Action menu for the Users - Draft - Delete Drafts. */
    public void clickBulkActionDeleteDrafts() {
        usersPage().getSelectAllCheckboxClick().click();
        usersPage().getAssignButton().parent().click();
        usersDraftTab().getDeleteBulkAction().click();
    }

    /** Cancel 'Save as draft' action. */
    public void cancelSaveAsDraft() {
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getSaveAsDraftButton().click();
        saveAsDraftService().clickCancel();
    }

    /** Open 'Draft' list. */
    public void openDraftUsersList() {
        await().pollDelay(1, SECONDS).until(() -> true);
        navigationCompany().getUsersNavigationItem().click();
        await().pollDelay(1, SECONDS).until(() -> true);
        usersPage().getDraftTab().click();
    }
}
