package com.practis.web.util;

import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.util.SelenideJsUtils.jsClick;

public class PractisUtils {

    public static void clickOutOfTheForm() {
        jsClick($("a[data-test='sidebar-teams']"));
    }

    public static void clickOutOfTheFormForPopup() {
        jsClick($(".sc-dAQWYi.jTtIvy"));
    }
}
