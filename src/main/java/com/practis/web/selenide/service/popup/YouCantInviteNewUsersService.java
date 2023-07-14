package com.practis.web.selenide.service.popup;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.youNeedMoreSeats;

public class YouCantInviteNewUsersService {

    /** Click "Manage Users" button. */
    public void clickManageUsersButton() {
        youNeedMoreSeats().getManageInvitationsButton().click();
    }

    /** Click "Set a Limit" button. */
    public void clickSetALimitButton() {
        youNeedMoreSeats().getRequestLimitChangeButton().click();
    }
}
