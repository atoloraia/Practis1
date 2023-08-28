package com.practis.web.selenide.page.company.user;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UserProfilePage {

    private final SelenideElement userProfileTitle =
            $("div[data-test='user-profile-page-subtitle']");

    private final SelenideElement updatedTimestamp =
            $("span[data-test='user-profile-timestamp-label']");
    private final SelenideElement updateButton =
            $("button[data-test='user-profile-timestamp-refresh']");

    private final SelenideElement userRole = $("p[data-test='user-profile-role']");
    private final SelenideElement userAvatar = $("div[data-test='user-profile-avatar']");
    private final SelenideElement userName = $("div[data-test='user-profile-full-name']");
    private final SelenideElement userEmail = $("div[data-test='user-profile-email']");
    private final SelenideElement pendingRegistrationLabel =
            $("span[data-test='pending-registration-label']");
    private final SelenideElement pendingRegistrationHourglass =
            $("div[data-test='user-profile-avatar-pending-icon']");
    private final SelenideElement assignButton = $("div[data-test='user-profile-assign']");
    private final SelenideElement userSettingsButton = $("button[data-test='user-settings']");
    private final SelenideElement nudgeButton = $("button[data-test='nudge-user']");

    private final SelenideElement searchField = $("input[data-test='user-profile-search-input']");
    private final SelenideElement filtersButton =
            $("button[data-test='user-profile-filters-button']");
    private final SelenideElement paginationBackButton =
            $("button[data-test='user-profile-paging-prev']");
    private final SelenideElement paginationNextButton =
            $("button[data-test='user-profile-paging-next']");

    private final SelenideElement selectAllCheckbox =
            $("td[data-test-custom-name='user-profile-master-checkbox-column']");
    private final SelenideElement practisSetColumn = $("th[data-test='practis-sets-column']");
    private final ElementsCollection practisSetTitle = $$("div[data-test='user-practis-set-name']");
    private final SelenideElement dueDateColumn = $("th[data-test='due-date-column']");
    private final SelenideElement progressColumn = $("th[data-test='progress-column']");
    private final SelenideElement accuracyColumn = $("th[data-test='accuracy-column']");
    private final SelenideElement trainingTimeColumn = $("th[data-test='training-time-column']");
    private final SelenideElement assignedColumn = $("th[data-test='assigned-column']");
    private final SelenideElement startedColumn = $("th[data-test='started-column']");
    private final SelenideElement lastTrainingColumn = $("th[data-test='last-training-column']");

    private final ElementsCollection psRow = $$("tr[data-test='user-practis-set-item']");

    private final SelenideElement noPractisSetImage =
            $("div[data-test='no-user-practis-sets-icon']");
    private final SelenideElement noPractisSetText =
            $("div[data-test='no-user-practis-sets-label']");
}
