package com.practis.web.page.scenario;

import static com.practis.utils.AwaitUtils.awaitSeconds;
import static com.practis.utils.SeleniumUtils.executeJs;
import static com.practis.utils.SeleniumUtils.setDivText;
import static java.util.Optional.ofNullable;
import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.LINK_TEXT;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewScenarioInput;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
public class ScenarioNewPage extends AbstractScenarioPage {

  private static final int GENERATE_AUDIO_AWAIT_TIMEOUT = 20;

  private final WebDriver driver;

  @FindBy(how = LINK_TEXT, linkText = "+ Add a customer line")
  private WebElement customerLineLink;

  @FindBy(how = LINK_TEXT, linkText = "Add a rep line +")
  private WebElement repLineLink;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.iInfWM.primary")
  private WebElement publishButton;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.hrHHUu")
  private WebElement saveAsDraftButton;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.gitJLL.with-icon")
  private WebElement generateAllButton;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.dpIUpb.inverse")
  private WebElement popupDiscardButton;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.dpIUpb.primary")
  private WebElement popupSaveButton;

  @FindBy(how = CSS, css = "div.sc-bYwdaj.erzGAO")
  private List<WebElement> lineDeleteLinks;

  /**
   * To be added.
   */
  public ScenarioNewPage fillForm(final NewScenarioInput input) {
    this.fillTitle(input)
        .fillDescription(input)
        .addCustomerLine()
        .fillCustomerLine(input)
        .addRepLine()
        .fillRepLine(input);
    return this;
  }

  /**
   * To be added.
   */
  public ScenarioNewPage fillTitle(final NewScenarioInput input) {
    ofNullable(input.getTitle()).ifPresent(titleField::sendKeys);
    return this;
  }

  public ScenarioNewPage fillDescription(final NewScenarioInput input) {
    ofNullable(input.getDescription()).ifPresent(descriptionField::sendKeys);
    return this;
  }

  /**
   * To be added.
   */
  public ScenarioNewPage fillCustomerLine(final NewScenarioInput input) {
    ofNullable(input.getCustomerLine())
        .ifPresent(line -> setDivText(driver, customerLines.get(0), line));
    return this;
  }

  /**
   * To be added.
   */
  public ScenarioNewPage fillRepLine(final NewScenarioInput input) {
    ofNullable(input.getRepLine())
        .ifPresent(line -> setDivText(driver, repLines.get(0), line));
    return this;
  }

  public ScenarioNewPage addCustomerLine() {
    customerLineLink.click();
    return this;
  }

  public ScenarioNewPage deleteCustomerLine() {
    lineDeleteLinks.get(0).click();
    return this;
  }

  public ScenarioNewPage addRepLine() {
    repLineLink.click();
    return this;
  }

  public ScenarioNewPage deleteRepLine() {
    lineDeleteLinks.get(0).click();
    return this;
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public ScenarioNewPage generateAudio() {
    //wait before generate
    Thread.sleep(1000);
    generateAllButton.click();
    return this;
  }

  /**
   * To be added.
   */
  public ScenarioNewPage waitForGenerate(final int numberOfLinks) {
    awaitSeconds(GENERATE_AUDIO_AWAIT_TIMEOUT, () -> playButtons.size() == numberOfLinks);
    return this;
  }

  /**
   * To be added.
   */
  public void publish() {
    publishButton.click();
  }

  /**
   * To be added.
   */
  public void saveAsDraft() {
    saveAsDraftButton.click();
  }

  public ScenarioNewPage clickOutOfForm() {
    executeJs(driver, "document.querySelectorAll('div.sc-QsvGH.cwaeNi')[0].click()");
    return this;
  }

  public ScenarioNewPage clickDiscardOnPopup() {
    popupDiscardButton.click();
    return this;
  }

  public ScenarioNewPage clickSaveOnPopup() {
    popupSaveButton.click();
    return this;
  }
}
