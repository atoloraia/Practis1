package com.practis.web.selenide.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class ConfigurationLoader {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @SneakyThrows
  public static <T> T loadConfig(final String path, final Class<T> configClass) {
    return MAPPER.readValue(ConfigurationLoader.class.getResource(path), configClass);
  }

}
