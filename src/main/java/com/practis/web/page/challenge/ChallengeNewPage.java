package com.practis.web.page.challenge;

import static com.practis.utils.AwaitUtils.awaitSeconds;
import static com.practis.utils.SeleniumUtils.setDivText;
import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.LINK_TEXT;

import com.practis.configuration.selenium.element.EmptyElement;
import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewChallengeInput;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
public class ChallengeNewPage {

  private static final int PAGE_LOAD_TIMEOUT = 10;
  private static final int GENERATE_ALL_TIMEOUT = 10;

  private final WebDriver driver;

  @FindBy(how = CSS, css = "input.sc-iqsfdx.fmTFXi.sc-cvMsJS.hsszVc")
  private WebElement titleField;

  @FindBy(how = CSS, css = ".sc-ksdxAp.dxLFpd")
  private WebElement descriptionField;

  @FindBy(how = CSS, css = "div.sc-bKvjpG.drXqTs")
  private List<WebElement> replicaFields;

  @FindBy(how = LINK_TEXT, linkText = "+ Add a customer line")
  private WebElement addCustomerLineLink;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.gitJLL")
  private WebElement generateForAllButton;

  @FindBy(how = CSS, css = "button[title='Play']")
  private List<WebElement> playButtons;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.iInfWM")
  private WebElement submitButton;

  /**
   * Create a new Challenge.
   */
  public ChallengeNewPage fillForm(final NewChallengeInput input) {
    awaitSeconds(PAGE_LOAD_TIMEOUT, () -> !(titleField instanceof EmptyElement));

    titleField.sendKeys(input.getTitle());
    descriptionField.sendKeys(input.getDescription());
    setDivText(driver, replicaFields.get(0), input.getCustomerLine());
    addCustomerLineLink.click();
    setDivText(driver, replicaFields.get(1), input.getRepLine());

    generateForAllButton.click();
    awaitSeconds(GENERATE_ALL_TIMEOUT, () -> playButtons.size() == 2);
    return this;
  }

  public void submit() {
    submitButton.click();
  }

  //
  //  /**
  //   * Finds "Challenge Set Title" field by css selector.
  //   */
  //  public HtmlElement<ChallengeNewPage> findTitleField() {
  //    return byCssFirst("input.sc-iqsfdx.fmTFXi", TITLE_FIELD_WAIT_TIMEOUT)
  //        .orElseThrow(exception("Challenge Title input is not found on Challenge page"));
  //  }
  //
  //
  //  /**
  //   * Finds "Description" field by css selector.
  //   */
  //  public HtmlElement<ChallengeNewPage> findDescriptionField() {
  //    return byCssFirst("textarea.sc-ksdxAp.dxLFpd")
  //        .orElseThrow(exception("Challenge Description input is not found on Challenge page"));
  //  }
  //
  //  /**
  //   * Find Add Customer Button button.
  //   */
  //  public HtmlElement<ChallengeNewPage> findLink(final String title) {
  //    return getLinks().stream()
  //        .filter(link -> link.getText().equals(title))
  //        .findFirst()
  //        .orElseThrow(exception(title + " link not found"));
  //  }
  //
  //  private List<HtmlElement<ChallengeNewPage>> getLinks() {
  //    return byCss("a.sc-jRQAMF.cimCkU");
  //  }
  //
  //  /**
  //   * Find Rep Field button.
  //   */
  //  public HtmlElement<ChallengeNewPage> findReplicaField(final int index) {
  //    return byCss("div.sc-hclBlX.jNaEvb").stream()
  //        .skip(index)
  //        .findFirst()
  //        .orElseThrow(() -> new RuntimeException("Add a Customer/Rep line field is not found"));
  //  }
}
