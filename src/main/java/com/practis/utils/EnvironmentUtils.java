package com.practis.utils;

import static com.practis.dictionary.EnvironmentDictionary.AUTOMATION_RUN_CI;
import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvironmentUtils {

    public static boolean isRunOnContinuousIntegration() {
        return ofNullable(getenv(AUTOMATION_RUN_CI)).isPresent();
    }
}
