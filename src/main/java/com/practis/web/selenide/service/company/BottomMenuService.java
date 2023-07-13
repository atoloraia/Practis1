package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.bottomProfileMenu;

public class BottomMenuService {

    /** Click on Bottom Menu */
    public void clickOnBottomMenu() {
        bottomProfileMenu().getUserName().click();
    }

    /** Click on Log Out */
    public void clickOnLogOut() {
        bottomProfileMenu().getBottomMenuActions().get(0).click();
    }

    /** Click on Company Settings */
    public void clickOnCompanySettings() {
        bottomProfileMenu().getBottomMenuActions().get(1).click();
    }

    /** Click on My Settings */
    public void clickOnMySettings() {
        bottomProfileMenu().getBottomMenuActions().get(2).click();
    }
}
