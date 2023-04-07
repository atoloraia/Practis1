package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ThisDidNotWorkPage {

    private final SelenideElement InvalidInviteImg = $("$(img[data-test='error-image'])");
    private final SelenideElement thisDidNotWorkTitle = $("$(div[data-test='error-title'])");
    private final SelenideElement thisDidNotWorkDescription =
            $("$(div[data-test='error-description'])");
    private final SelenideElement contactUsDescription = $("$(div[data-test='contact-us'])");
}
