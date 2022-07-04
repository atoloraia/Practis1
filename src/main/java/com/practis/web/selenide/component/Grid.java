package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import java.util.Locale;
import lombok.Getter;

@Getter
public class Grid {

  private final ElementsCollection tableRows = $$("tbody tr[data-test='table-row']");

  /**
   * To be added.
   */
  public GridRow getRow(final String value) {
    final var row = tableRows.find(Condition.matchText(value));
    return new GridRow(row);
  }
}
