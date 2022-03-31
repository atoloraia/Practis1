package com.practis.configuration.web.properties;

import com.practis.configuration.dto.Credentials;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("web.application")
@Component
@Data
public class WebApplicationProperties {

  private String url;
  private Credentials adminCredentials;
  private String adminCompanyName;
  private String automationCompanyName;
}
