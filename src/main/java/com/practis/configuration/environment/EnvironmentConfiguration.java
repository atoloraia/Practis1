package com.practis.configuration.environment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class EnvironmentConfiguration {

  /**
   * Adds custom property source to configure environment.
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer(
      final ResourceLoader resourceLoader
  ) {
    final var ppc = new PropertySourcesPlaceholderConfigurer();
    final var osName = System.getProperty("os.name");
    if (osName.contains("Mac")) {
      ppc.setLocations(resourceLoader.getResource("/os/mac.properties"));
    }
    if (osName.equals("Linux")) {
      ppc.setLocations(resourceLoader.getResource("/os/docker.properties"));
    }
    if (osName.contains("Windows")) {
      ppc.setLocations(resourceLoader.getResource("/os/windows.properties"));
    }
    ppc.setIgnoreUnresolvablePlaceholders(true);
    return ppc;
  }
}
