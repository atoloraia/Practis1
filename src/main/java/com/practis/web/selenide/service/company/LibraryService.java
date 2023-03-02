package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.filter;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.statusModuleService;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenideJsUtils.jsClick;

public class LibraryService {

    /** Filter by archived item. */
    public void filterByArchivedItems() {
        libraryPage().getFiltersButton().click();
        // await().pollDelay(FIVE_SECONDS).until(() -> true);
        awaitSoft(10, () -> filter().getLibraryClearButton().exists());
        jsClick(filter().getLibraryClearButton());
        statusModuleService().selectArchivedStatus();
        jsClick(filter().getApplyLibraryFilterButton());
    }

    /** Filter by draft item. */
    public void filterByDraftItems() {
        libraryPage().getFiltersButton().click();
        filter().getLibraryClearButton().click();
        statusModuleService().selectDraftStatus();
        jsClick(filter().getApplyLibraryFilterButton());
    }
}
