package com.practis.web.selenide.service.popup;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.youNeedMoreSeats;

public class YouNeedMoreSeatsPopUpService {

    /** Click "Manage Users" button. */
    public void clickManageUsersButton() {
        youNeedMoreSeats().getManageUsersButton().click();
    }

    /** Click "Set a Limit" button. */
    public void clickSetALimitButton() {
        youNeedMoreSeats().getSetALimitButton().click();
    }
}
