package com.practis.web.selenide.component;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class NewItemSelector {

    private final SelenideElement newItemSelector =
            $("div[data-test='actionDropDownToggleButton']");
    private final ElementsCollection newItemRows = $$("a[data-test='dropDownListLink']");

    /** To be added. */
    public void create(final String itemName) {
        awaitSoft(10, () -> getNewItemSelector().isDisplayed());
        getNewItemSelector().click();
        getRow(itemName).click();
    }

    public SelenideElement getRow(final String itemName) {
        return newItemRows.find(text(itemName));
    }
}
