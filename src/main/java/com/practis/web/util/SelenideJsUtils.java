package com.practis.web.util;

import static com.codeborne.selenide.Configuration.fastSetValue;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

public class SelenideJsUtils {

    public static void jsClick(final WebElement element) {
        executeJavaScript("arguments[0].click()", element);
    }

    public static void hackedSetValue(final SelenideElement element, final String value) {
        final var prevValue = fastSetValue;
        fastSetValue = false;
        final var wrapped = Selenide.$(element);
        wrapped.clear();
        wrapped.setValue(value);
        fastSetValue = prevValue;
    }
}
