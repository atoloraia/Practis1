package com.practis.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class ConfigurationLoader {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @SneakyThrows
  public static <T> T loadConfig(final String path, final Class<T> configClass) {
    return MAPPER.readValue(ConfigurationLoader.class.getResource(path), configClass);
  }

  @SneakyThrows
  public static <T> T loadJson(final Object json, final Class<T> dataClass) {
    return MAPPER.readValue(json.toString(), dataClass);
  }
}
