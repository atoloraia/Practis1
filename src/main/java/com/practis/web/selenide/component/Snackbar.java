package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class Snackbar {

  private final SelenideElement message = $("div[data-test='message-box']");
}
