package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class Search {

    private final SelenideElement searchFieldElement = $("input[data-test*='-search']");
    private final SelenideElement userSearchFieldElement = $("input[data-test*='search']");

    private final SelenideElement searchField = $("input[data-test*='search-input']");
    private final SelenideElement searchFieldIcon = $("div[data-test*='input-icon']");
    private final SelenideElement searchFieldClearButton = $("div[data-test*='input-clear']");

    /** Put input to search field. */
    public void search(final String input) {
        searchFieldElement.setValue(input.substring(0, input.length() - 1));
        searchFieldElement.append(input.substring(input.length() - 1));
    }

    /** Put input to search field. */
    public void searchPhoneNumber(final int input) {
        String phone = Integer.toString(input);
        searchFieldElement.setValue(phone.substring(0, phone.length() - 1));
        searchFieldElement.append(phone.substring(phone.length() - 1));
    }

    /** Put input to search field. */
    public void userSearch(final String input) {
        userSearchFieldElement.setValue(input.substring(0, input.length() - 1));
        userSearchFieldElement.append(input.substring(input.length() - 1));
    }

    /** Put input to search field. */
    public void userSearchWithUpperCases(final String input) {
        userSearchFieldElement.setValue(input.toUpperCase().substring(0, input.length() - 1));
        userSearchFieldElement.append(input.toUpperCase().substring(input.length() - 1));
    }
}
