package com.practis.web.selenide.configuration.data;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.rest.dto.user.CreateAdminRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewPractisAdminData {

    public static CreateAdminRequest getNewPractisAdminInput() {
        return getNewAdminInputs().get(0);
    }

    /** Get all Admin input data. */
    public static List<CreateAdminRequest> getNewAdminInputs() {
        return Stream.of(
                        loadConfig(
                                "/configuration/web/input/CompanyAdmin.json",
                                CreateAdminRequest[].class))
                .collect(Collectors.toList());
    }
}
