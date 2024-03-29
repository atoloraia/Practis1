package com.practis.web.selenide.service.popup;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.limitUsersPopUp;

public class YouNeedMoreSeatsPopUpService {

    /** Click "Manage Users" button. */
    public void clickManageUsersButton() {
        limitUsersPopUp().getManageInvitationsButton().click();
    }

    /** Click "Set a Limit" button. */
    public void clickSetALimitButton() {
        limitUsersPopUp().getRequestLimitChangeButton().click();
    }
}
