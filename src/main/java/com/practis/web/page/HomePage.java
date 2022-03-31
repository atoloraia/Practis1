package com.practis.web.page;

import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
public class HomePage {

  @FindBy(how = CSS, css = "button[title=\"Log In\"]")
  private WebElement loginButton;

  public void login() {
    loginButton.click();
  }
}
