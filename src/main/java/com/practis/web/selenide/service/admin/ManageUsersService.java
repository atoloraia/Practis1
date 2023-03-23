package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.web.selenide.component.GridRow;

public class ManageUsersService {

    /** Search User on grid by User First Name. */
    public GridRow searchUser(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().userSearch(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Search User on grid by User First Name with Upper cases. */
    public GridRow searchUserWithUpperCases(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().userSearchWithUpperCases(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }
}
