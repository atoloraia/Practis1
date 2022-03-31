package com.practis.web.page.admin;

import static com.practis.utils.AwaitUtils.awaitSeconds;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewCompanyInput;
import com.practis.web.page.common.component.admin.SnackbarComponent;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
public class CompanyNewPage {

  private static final int PAGE_LOAD_TIMEOUT = 30;

  @Getter
  private final SnackbarComponent snackbar;

  @FindBy(how = CSS, css = "input[name*='name']")
  private List<WebElement> nameFields;

  @FindBy(how = CSS, css = "input[name*='ownerEmail']")
  private List<WebElement> emailFields;

  @FindBy(how = CSS, css = "input[name*='ownerFirstName']")
  private List<WebElement> firstNameFields;

  @FindBy(how = CSS, css = "input[name*='ownerLastName']")
  private List<WebElement> lastNameFields;

  @FindBy(how = CSS, css = "button[title='Invite']")
  @Getter
  private WebElement inviteButton;

  @FindBy(how = CSS, css = "a.sc-jRQAMF.cimCkU")
  private WebElement addRowLink;

  /**
   * To be added.
   */
  public CompanyNewPage fillForm(final int row, final NewCompanyInput input) {
    awaitSeconds(PAGE_LOAD_TIMEOUT, () -> nameFields.size() > 0);
    nameFields.get(row).sendKeys(input.getName());
    emailFields.get(row).sendKeys(input.getEmail());
    firstNameFields.get(row).sendKeys(input.getFirstName());
    lastNameFields.get(row).sendKeys(input.getLastName());
    return this;
  }

  /**
   * To be adde.
   */
  public void setRowNum(int size) {
    while (nameFields.size() != size) {
      addRowLink.click();
      setRowNum(size);
    }
  }

  public void invite() {
    inviteButton.click();
  }
}
