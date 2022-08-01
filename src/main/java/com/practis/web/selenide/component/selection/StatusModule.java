package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class StatusModule {
  private final ElementsCollection moduleTitle = $$(".sc-fBgrOm.cKwLRc");
  private final ElementsCollection checkboxButton = $$(".sc-hJhJlY.gyEmir");
  private final SelenideElement checkboxButtonArchived = $(".sc-hJhJlY.gcLrPm");
  private final ElementsCollection statusTitles = $$(".sc-iukwUI.kqgpwO");
}
