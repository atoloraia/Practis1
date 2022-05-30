package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.utils.AwaitUtils.awaitElementCollectionSize;
import static com.practis.utils.AwaitUtils.awaitElementEnabled;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelChallenge;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.util.SelenideSetDivUtilUtil.setDivText;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewScenarioInput;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class ScenarioCreatePage {

  private final SelenideElement titleField = $(".sc-fotPbf.igAmbG.sc-cYvfTo.dVexzz");
  private final ElementsCollection lineLinks = $$("a.sc-nVjpj.clvsrj");
  private final ElementsCollection customerField =
      $$("div[placeholder='Write customer’s line here']");
  private final ElementsCollection repField =
      $$("div[placeholder='Write representative’s line here']");
  private final SelenideElement descriptionField = $(".sc-hctthz.pSSWt");
  private final SelenideElement publishButton = $(".sc-jgrIVw.lclJYS.primary");
  private final SelenideElement saveAsDraftButton = $(".sc-jgrIVw.bHkvOE.inverse");
  private final SelenideElement generateForAllButton = $("button[title='Generate for All']");
  private final SelenideElement deleteLine = $(".sc-exjpvi.kiQA-dV");
  private final SelenideElement addLabels = $(".sc-fLWQsF.mFUbO  ");
  private final ElementsCollection playButtons = $$("button[title='Play']");
  private final SelenideElement deleteCustomerLine = $(".sc-exjpvi.kiQA-dV");
  private static final int GENERATE_ALL_TIMEOUT = 10;


  /**
   * Fill Title.
   */
  public void fillTitle(final NewScenarioInput inputData) {
    titleField.append(inputData.getTitle());
  }

  /**
   * Fill Scenario Form.
   */
  @SneakyThrows
  public void fillForm(final NewScenarioInput inputData, final String label) {
    fillTitle(inputData);
    descriptionField.append(inputData.getDescription());

    addLabels.click();
    labelChallenge().selectLabel(label).clickAddLabel();

    //Check snackbar message "Challenge published"
    snackbar().getMessage().shouldBe(exactText("labels have been assigned to Scenario"));

    lineLinks.get(0).click();
    setDivText(customerField.get(0),
        inputData.getCustomerLine());

    lineLinks.get(1).click();
    setDivText(repField.get(0),
        inputData.getRepLine());
    awaitElementEnabled(10, () -> generateForAllButton).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, () -> playButtons, 1);
  }

  /**
   * Fill Customer Line.
   */
  public void fillCustomerLine(final NewScenarioInput inputData) {
    lineLinks.find(Condition.text("+ Add a customer line")).click();
    setDivText(customerField.get(0),
        inputData.getCustomerLine());
  }

  /**
   * Fill rep Line.
   */
  public void fillRepLine(final NewScenarioInput inputData) {
    lineLinks.find(Condition.text("Add a rep line +")).click();
    setDivText(repField.get(0),
        inputData.getRepLine());
  }

  /**
   * Generate for all.
   */
  @SneakyThrows
  public void generateForAll() {
    Thread.sleep(1000);
    awaitElementEnabled(10, () -> generateForAllButton).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, () -> playButtons, 1);
  }


}
