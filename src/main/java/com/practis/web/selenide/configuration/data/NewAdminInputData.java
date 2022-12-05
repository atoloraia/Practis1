package com.practis.web.selenide.configuration.data;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.NewAdminInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewAdminInputData {

    public static NewAdminInput getNewAdminInput() {
        return getNewAdminInputs().get(0);
    }

    /** Get all Admin input data. */
    public static List<NewAdminInput> getNewAdminInputs() {
        return Stream.of(loadConfig("/configuration/web/input/admin.json", NewAdminInput[].class))
                .collect(Collectors.toList());
    }
}
