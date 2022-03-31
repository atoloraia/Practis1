package com.practis.web.page.common;

import com.practis.configuration.selenium.support.PractisPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

@PractisPage
public class PractisBasePage {

  @Getter
  @Autowired
  private WebDriver driver;
}
