package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class FeedPage {

  private final SelenideElement feedButton = $(".sc-dkqQaW.IaxPX");

}
