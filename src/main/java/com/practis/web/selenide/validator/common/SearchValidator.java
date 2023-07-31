package com.practis.web.selenide.validator.common;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.codeborne.selenide.ElementsCollection;
import java.util.List;

public class SearchValidator {

    /** Assert 'Administrators' screen: */
    public static void assertSearchField() {
        awaitSoft(10, () -> search().getSearchField().isEnabled());
        search().getSearchField().shouldBe(enabled);
        search().getSearchField().shouldBe(visible);
        search().getSearchField().shouldBe(attribute("font-size", "13px"));
        search().getSearchField().shouldBe(enabled);
        search().getSearchField().shouldBe(attribute("type", "text"));
        search().getSearchFieldIcon().shouldBe(visible);
        search().getSearchFieldClearButton().shouldNotBe(visible);
    }

    /** Assert no search results. */
    public static void assertNoSearchResult() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        search().getSearchField().shouldBe(visible);
        search().getSearchFieldClearButton().shouldBe(visible);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1Char(
            final String searchString, List<ElementsCollection> row) {
        final var input = searchString.charAt(searchString.length() - 1);
        search().getSearchField().append(String.valueOf(input));
        search().getSearchFieldClearButton().shouldBe(visible);
        row.get(0).shouldBe();
    }

    /** Assert clean search. */
    public static void assertCleanSearch() {
        search().getSearchField().append(("check clean icon"));
        search().getSearchFieldClearButton().shouldBe(visible);
        search().getSearchFieldClearButton().click();
        search().getSearchFieldClearButton().shouldNotBe(visible);
    }
}
