package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TeamMemberStatus {

    private final ElementsCollection statusRows =
            $$("div[data-test='team-member-status-item-container']");

    private final SelenideElement teamMemberStatusTitle = $("span[data-test='team-member-status']");
    private final SelenideElement practisSetStatusTitle = $("span[data-test='practisset-status']");
    private final SelenideElement notStartedStatus = $("div[data-test='not-started-label']");
    private final SelenideElement notStartedCheckbox = $("div[data-test='not-started-view']");
    private final SelenideElement inProgressStatus = $("div[data-test='in-progress-label']");
    private final SelenideElement inProgressCheckbox = $("div[data-test='in-progress-view']");
    private final SelenideElement completedStatus = $("div[data-test='completed-label']");
    private final SelenideElement completedCheckbox = $("div[data-test='completed-view']");
}
