package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.nudgePopup;

public class NudgeUserService {

    /** Click fill Title. */
    public void sendNudge(String text) {
        nudgePopup().getMessageField().append(text);
        nudgePopup().getApplyButton().click();
    }
}
