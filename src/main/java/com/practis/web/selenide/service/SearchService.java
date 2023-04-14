package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;

public class SearchService {

    /** Search perform */
    public void searchPerform(final String input) {
        search().getSearchField().append(input);
    }

    /** Clear search */
    public void clearSearch() {
        search().getSearchFieldClearButton().click();
    }

    /** Search after entering 1 characters. */
    public static void searchAfter1Char(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        search().getSearchField().append(String.valueOf(input));
    }
}
