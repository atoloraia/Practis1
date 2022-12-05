package com.practis.web.selenide.configuration.data.company;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.NewTeamInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewTeamInputData {

    public static NewTeamInput getNewTeamInput() {
        return getNewTeamInputs().get(0);
    }

    /** Get all Team input data. */
    public static List<NewTeamInput> getNewTeamInputs() {
        return Stream.of(
                        loadConfig(
                                "/configuration/web/input/company/team.json", NewTeamInput[].class))
                .collect(Collectors.toList());
    }
}
