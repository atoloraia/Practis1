package com.practis.web.page.practis;

import static com.practis.utils.AwaitUtils.awaitSeconds;
import static com.practis.utils.SeleniumUtils.doubleClick;
import static com.practis.utils.SeleniumUtils.jsClick;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.element.EmptyElement;
import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.NewPractisSetInput;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
public class PractisSetNewPage {

  private static final int PAGE_LOAD_TIMEOUT = 10;
  private static final int SCENARIOS_LIST_WAIT_TIMEOUT = 10;
  private static final int CONFIRMATION_POPUP_OPEN_TIMEOUT = 1;

  private final WebDriver driver;

  @FindBy(how = CSS, css = "input.sc-iqsfdx.fmTFXi.sc-fKlbSM.hLDXKr")
  private WebElement titleField;

  @FindBy(how = CSS, css = "textarea.sc-ksdxAp.dxLFpd")
  private WebElement descriptionField;

  @FindBy(how = CSS, css = "div.sc-bXyQnh.eyoWso")
  private List<WebElement> scenarios;

  @FindBy(how = CSS, css = "button.sc-bkkfTU.iInfWM")
  private WebElement publishButton;

  @FindBy(how = CSS, css = "div.sc-hBURRC.jSdIQy")
  private WebElement confirmationButton;

  /**
   * To be added.
   */
  public PractisSetNewPage fillForm(final NewPractisSetInput input) {
    awaitSeconds(PAGE_LOAD_TIMEOUT, () -> !(titleField instanceof EmptyElement));

    titleField.sendKeys(input.getTitle());
    descriptionField.sendKeys(input.getDescription());

    awaitSeconds(SCENARIOS_LIST_WAIT_TIMEOUT, () -> scenarios.size() > 0);
    scenarios.stream().findFirst().ifPresent(element -> doubleClick(driver, element));

    return this;
  }

  /**
   * To be added.
   */
  @SneakyThrows
  public void publish() {
    Thread.sleep(3000);

    publishButton.click();
    findConfirmationButton().ifPresentOrElse(element -> jsClick(driver, element),
        () -> {
          throw new RuntimeException();
        });
  }

  /**
   * To be added.
   */
  @SneakyThrows
  private Optional<WebElement> findConfirmationButton() {
    Thread.sleep(3000);

    return Optional.of(confirmationButton)
        .filter(element -> !(element instanceof EmptyElement));
  }
}
