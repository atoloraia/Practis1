package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class Grid {

  private final ElementsCollection tableRows = $$("tbody tr[data-test='tableRow']");

  /**
   * To be added.
   */
  public GridRow getRow(final String value) {
    final var row = tableRows.find(Condition.text(value));
    return new GridRow(row);
  }
}
