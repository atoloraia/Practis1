package com.practis.support;

import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;

import com.practis.web.selenide.component.CompanySelector;
import java.security.cert.Extension;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AdminCampaignTest implements BeforeEachCallback {

  private final CompanySelector companySelector = new CompanySelector();

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    final var expectedCampaign = webApplicationConfig();
  }
}
