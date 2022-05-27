package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.utils.AwaitUtils.awaitElementCollectionSize;
import static com.practis.utils.AwaitUtils.awaitElementEnabled;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelChallenge;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.util.SelenideSetDivUtilUtil.setDivText;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewChallengeInput;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class ChallengeCreatePage {

  private final SelenideElement form = $("div.sc-jPnore.iicaUu");
  private final SelenideElement titleField = $("input.sc-fotPbf.igAmbG.sc-jvtoKZ.kSnnXw");
  private final SelenideElement descriptionField = $("textarea[placeholder='Description']");
  private final SelenideElement customerLine = $("div.sc-bYcdLl.cMDhei");
  private final SelenideElement addCustomerLineButton = $("a.sc-nVjpj.clvsrj");
  private final SelenideElement addLabels = $(".sc-fLWQsF.mFUbO  ");
  private final SelenideElement generateForAllButton = $("button[title='Generate for All']");
  private final SelenideElement publishButton = $("button.sc-jgrIVw.lclJYS.primary");
  private final SelenideElement saveAsDraftButton = $("button.sc-jgrIVw.bHkvOE.inverse");
  private final SelenideElement deleteCustomerLine = $(".sc-exjpvi.kiQA-dV");
  private final ElementsCollection playButtons = $$("button[title='Play']");
  private static final int GENERATE_ALL_TIMEOUT = 10;

  /**
   * Fill Title.
   */
  public void fillTitle(final NewChallengeInput inputData) {
    titleField.append(inputData.getTitle());
  }

  /**
   * Fill Title and Customer Line.
   */
  public void fillTitleWithCustomerLine(final NewChallengeInput inputData) {
    titleField.append(inputData.getTitle());
    setDivText(customerLine, inputData.getCustomerLine());
    awaitElementEnabled(10, () -> generateForAllButton).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, () -> playButtons, 1);
  }

  /**
   * Fill Customer Line.
   */
  public void fillCustomerLine(final NewChallengeInput inputData) {
    setDivText(customerLine, inputData.getCustomerLine());
    awaitElementEnabled(10, () -> generateForAllButton).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, () -> playButtons, 1);
  }

  /**
   * Fill Add New Challenge form.
   */
  @SneakyThrows
  public void fillForm(final NewChallengeInput inputData, final String label) {
    fillTitle(inputData);
    descriptionField.append(inputData.getDescription());

    addLabels.click();
    labelChallenge().selectLabel(label).clickAddLabel();

    //Check snackbar message "Challenge published"
    snackbar().getMessage().shouldBe(exactText("labels have been assigned to Challenge"));

    setDivText(customerLine, inputData.getCustomerLine());
    awaitElementEnabled(10, () -> generateForAllButton).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, () -> playButtons, 1);
  }
}
