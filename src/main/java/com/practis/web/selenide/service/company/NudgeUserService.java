package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.nudgePopup;

public class NudgeUserService {

    /** Click fill Title. */
    public void fillText(String text) {
        nudgePopup().getMessageFieldText().append(text);
    }

    /** Click "Send" button. */
    public void clickSend() {
        nudgePopup().getApplyButton().click();
    }

    /** Click Save as Draft. */
    public void sendNudge(String text) {
        fillText(text);
        clickSend();
    }
}
