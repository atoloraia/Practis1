package com.practis.web.selenide.page.company.users;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersPendingTab {

    // Columns
    private final SelenideElement userColumn = $("th[data-test='name-column']");
    private final SelenideElement emailColumn = $("th[data-test='email-column']");
    private final SelenideElement roleColumn = $("th[data-test='role-column']");
    private final SelenideElement invitedByColumn = $("th[data-test='inviter-column']");
    private final SelenideElement invitedOnColumn = $("th[data-test='invitation-date-column']");

    private final SelenideElement labelsIcon = $("div[data-test='table-labels']");

    private final SelenideElement noUsersFoundIcon = $("div[data-test='no-found-results-icon']");
    private final SelenideElement noUsersFoundText = $("div[data-test='no-found-results-label']");

    private final SelenideElement noFilteredResultsIcon =
            $("div[data-test='no-filtered-results-icon']");
    private final SelenideElement noFilteredResultsText =
            $("div[data-test='no-filtered-results-label']");

    // 3-dot menu
    private final SelenideElement viewProfileAction = $("div[data-test='view-profile-action']");
    private final SelenideElement assignLabelsAction = $("div[data-test='assign-labels-action']");
    private final SelenideElement resendInviteAction = $("div[data-test='resend-invite-action']");
    private final SelenideElement copyInviteTextAction =
            $("div[data-test='copy-invite-text-action']");
    private final SelenideElement revokeAction = $("div[data-test='revoke-action']");
}
