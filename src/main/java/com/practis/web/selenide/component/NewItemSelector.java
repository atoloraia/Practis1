package com.practis.web.selenide.component;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NewItemSelector {

  private final SelenideElement newItemSelector = $("div[data-test='actionDropDownToggleButton']");
  private final ElementsCollection newItemRows = $$("a[data-test='dropDownListLink']");

  public SelenideElement getRow(final String itemName) {
    return newItemRows.find(text(itemName));
  }
}
