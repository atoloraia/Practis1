package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;

import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;

public class UserService {

    /**
     * Assert User: search, assert data on Pending list, open Profile and asserUser data.
     *
     * @return
     */
    public static GridRow searchPendingUser(final NewUserInput inputs) {
        return userService().searchUser(inputs.getEmail());
    }
}
