package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class FeedStatusModule {

  private final SelenideElement statusTitle = $(".sc-fBgrOm.cKwLRc");

  private final ElementsCollection status = $$(".sc-iukwUI.djFtvI");
  private final ElementsCollection statusCheckbox = $$(".sc-hJhJlY.gcLrPm");
}
