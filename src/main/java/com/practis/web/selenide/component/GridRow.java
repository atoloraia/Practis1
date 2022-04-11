package com.practis.web.selenide.component;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class GridRow {

  private final ElementsCollection columns;

  private final SelenideElement rowElement;
  private final SelenideElement headerRow = $("thead tr[data-test='adminTableHeaderRow']");

  GridRow(final SelenideElement rowElement) {
    this.rowElement = rowElement;
    columns = rowElement.$$("td[data-test='table-cell']");
  }

  public void click() {
    rowElement.click();
  }

  public SelenideElement get(final String headerName) {
    final var colIndex = getColumnIndex(headerName);
    return columns.get(colIndex);
  }

  public String getText(final String headerName) {
    final var colIndex = getColumnIndex(headerName);
    return columns.get(colIndex).text();
  }

  private int getColumnIndex(final String columnText) {
    final var headerCols = headerRow.$$x("child::*");
    final var headerCol = headerCols.find(text(columnText));
    return headerCols.indexOf(headerCol);
  }
}
