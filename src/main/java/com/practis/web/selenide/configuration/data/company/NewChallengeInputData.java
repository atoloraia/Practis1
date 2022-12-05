package com.practis.web.selenide.configuration.data.company;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.NewChallengeInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewChallengeInputData {

    public static NewChallengeInput getNewChallengeInput() {
        return getNewChallengeInputs().get(0);
    }

    /** Get all Challenge input data. */
    public static List<NewChallengeInput> getNewChallengeInputs() {
        return Stream.of(
                        loadConfig(
                                "/configuration/web/input/company/challenge.json",
                                NewChallengeInput[].class))
                .collect(Collectors.toList());
    }
}
