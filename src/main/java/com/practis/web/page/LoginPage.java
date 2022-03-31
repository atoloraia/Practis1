package com.practis.web.page;

import static java.util.Optional.ofNullable;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.dto.Credentials;
import com.practis.configuration.selenium.element.EmptyElement;
import com.practis.configuration.selenium.support.PractisPage;
import com.practis.web.element.CompanySnackbar;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
public class LoginPage {

  @Getter
  private final CompanySnackbar snackbar;

  @FindBy(how = CSS, css = "div.sc-giYgFv.gnbvMI")
  private WebElement logo;

  @FindBy(how = CSS, css = "input[name=\"email\"]")
  private WebElement emailField;

  @FindBy(how = CSS, css = "input[name=\"password\"]")
  private WebElement passwordField;

  @FindBy(how = CSS, css = "button[type=\"submit\"]")
  private WebElement loginButton;

  @FindBy(how = CSS, css = "div.sc-GamvQ.fFeDVP")
  private List<WebElement> validationMessages;

  public boolean isOnPage() {
    return logo instanceof EmptyElement;
  }

  /**
   * To be added.
   */
  public void login(Credentials credentials) {
    login(credentials.getEmail(), credentials.getPassword());
  }

  /**
   * To be added.
   */
  public void login(final String email, final String password) {
    ofNullable(email).ifPresent(emailField::sendKeys);
    ofNullable(password).ifPresent(passwordField::sendKeys);
    loginButton.click();
  }

  public String getValidationMessage(final int index) {
    return validationMessages.get(index).getText();
  }
}
