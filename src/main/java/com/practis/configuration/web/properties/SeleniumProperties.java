package com.practis.configuration.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "web.selenium")
@Component
@Data
public class SeleniumProperties {

  private String test;
  private String pathToDriver;
  private String pathToBinary;
  private String[] arguments;
}
