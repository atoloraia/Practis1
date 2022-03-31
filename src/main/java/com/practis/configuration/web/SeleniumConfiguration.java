package com.practis.configuration.web;

import static java.util.Optional.ofNullable;

import com.practis.configuration.web.properties.SeleniumProperties;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

  /**
   * Init {@link ChromeDriver}.
   */
  @Bean
  ChromeDriver chromeDriver(final SeleniumProperties properties) {
    System.setProperty("webdriver.chrome.driver", properties.getPathToDriver());
    final var options = new ChromeOptions();

    ofNullable(properties.getArguments()).ifPresent(options::addArguments);
    ofNullable(properties.getPathToBinary()).ifPresent(options::setBinary);

    return new ChromeDriver(options);
  }

}
