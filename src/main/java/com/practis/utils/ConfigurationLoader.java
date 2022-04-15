package com.practis.utils;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.SneakyThrows;

public class ConfigurationLoader {

  private static final ObjectMapper MAPPER;

  static {
    MAPPER = new ObjectMapper();
    MAPPER.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @SneakyThrows
  public static <T> T loadConfig(final String path, final Class<T> configClass) {
    return MAPPER.readValue(ConfigurationLoader.class.getResource(path), configClass);
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public static <T> List<T> loadConfigList(final String path, final Class<T> configClass) {
    System.out.printf(configClass.getName());
    return MAPPER.readValue(ConfigurationLoader.class.getResource(path),
        new TypeReference<List<T>>() {
        });
  }

  @SneakyThrows
  public static <T> T loadJson(final Object json, final Class<T> dataClass) {
    return MAPPER.readValue(json.toString(), dataClass);
  }
}
