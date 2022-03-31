package com.practis.web.element;

import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.utils.AwaitUtils;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RequiredArgsConstructor
@PractisPage
public class CompanySnackbar {

  @FindBy(how = CSS, css = ".sc-jrQzUz.kYiNmI")
  private WebElement snackbar;

  @FindBy(how = CSS, css = ".sc-gKckTs.gKvIyv")
  private WebElement errorSnackbar;

  /**
   * test.
   */
  public String getText() {
    return snackbar.getText();
  }

  /**
   * test.
   */
  public boolean isNotificationAppears() {
    try {
      AwaitUtils.awaitSeconds(5, () -> snackbar.getText().length() > 0);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String getErrorText() {
    return errorSnackbar.getText();
  }
}
