package com.practis.web.util;

import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelenideSetDivUtilUtil {

    /** To be added. */
    public static void setDivText(final SelenideElement element, final String input) {
        executeJavaScript("arguments[0].innerHTML = arguments[1]", element, input);
        executeJavaScript(
                "arguments[0].dispatchEvent(new InputEvent('input', {data: '', bubbles: true}))",
                element);
    }
}
