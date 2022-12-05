package com.practis.web.util;

import static com.codeborne.selenide.Selenide.executeJavaScript;

import org.openqa.selenium.WebElement;

public class SelenideJsUtils {

    public static void jsClick(final WebElement element) {
        executeJavaScript("arguments[0].click()", element);
    }
}
