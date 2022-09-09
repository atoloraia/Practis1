package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;

@Getter
public class StatusModule {

  private final ElementsCollection moduleTitle = $$(".sc-fBgrOm.cKwLRc");
  private final ElementsCollection status = $$(".sc-hGnhcH.eYXjfR");

}
