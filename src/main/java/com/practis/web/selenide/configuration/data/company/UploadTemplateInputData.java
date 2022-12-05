package com.practis.web.selenide.configuration.data.company;

import static com.practis.utils.ConfigurationLoader.loadConfig;

import com.practis.dto.NewUserInput;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UploadTemplateInputData {

    public static NewUserInput getUploadTemplateInput() {
        return getUploadTemplateInputs().get(0);
    }

    /** Get all User input data. */
    public static List<NewUserInput> getUploadTemplateInputs() {
        return Stream.of(
                        loadConfig(
                                "/configuration/web/input/company/template.json",
                                NewUserInput[].class))
                .collect(Collectors.toList());
    }
}
