package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class HomePage {

  private final SelenideElement loginButton = $("button[title='Log In']");

}
