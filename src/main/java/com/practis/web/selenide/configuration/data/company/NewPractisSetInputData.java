package com.practis.web.selenide.configuration.data.company;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.NewPractisSetInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewPractisSetInputData {

    public static NewPractisSetInput getNewPractisSetInput() {
        return getNewPractisSetInputs().get(0);
    }

    /** Get all Practis Set input data. */
    public static List<NewPractisSetInput> getNewPractisSetInputs() {
        return Stream.of(
                        loadConfig(
                                "/configuration/web/input/company/practisSet.json",
                                NewPractisSetInput[].class))
                .collect(Collectors.toList());
    }
}
