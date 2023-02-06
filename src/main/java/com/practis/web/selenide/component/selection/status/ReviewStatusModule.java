package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ReviewStatusModule {

    private final SelenideElement statusTitle = $("span[data-test='review-status-section-title']");

    private final SelenideElement needsReviewStatus =
            $("div[data-test='needs-review-checkbox-label']");
    private final SelenideElement checkedNeedsReviewCheckbox =
            $("input[data-test='needs-review-checkbox-checked']");

    private final SelenideElement reviewedStatus = $("div[data-test='reviewed-checkbox-label']");
    private final SelenideElement checkedReviewedCheckbox =
            $("input[data-test='reviewed-checkbox-checked']");
}
