package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class Filter {

    // filters elements: Selected counter, Apply Filter, Clear
    private final SelenideElement selectedFilterCriteriaCounter =
            $("span[data-test='total-selected-filters-text']");
    private final SelenideElement clearButton = $("button[data-test='clear-button']");
    private final SelenideElement libraryClearButton =
            $("button[data-test='library-filters-clear']");

    private final SelenideElement applyFilterButton = $("button[data-test='apply-filter-button']");
    private final SelenideElement applyLibraryFilterButton =
            $("button[data-test='library-filters-apply']");
}
