package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;

import com.practis.web.selenide.component.GridRow;

public class SearchService {

    /** Search perform */
    public void searchPerform(final String input) {
        search().getSearchField().append(input);
    }

    /** Search and return grid. */
    public GridRow searchRow(final String name) {
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
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
