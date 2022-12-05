package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;

public class NavigationCompanyService {

    /** Navigate to Users page. */
    public void openUsersPage() {
        navigationCompany().getUsersNavigationItem().click();
    }
}
