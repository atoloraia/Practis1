package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetCreatePage {

  private final SelenideElement addNewPractisSetTitle =
      $("input.sc-fotPbf.igAmbG.sc-cEqpQT.byyINI");
  private final SelenideElement notPublishedYetText = $(".sc-dsCHzD.Rxpra");

  private final SelenideElement titleField = $("input.sc-fotPbf.igAmbG.sc-gvnPBw.jAClVA");
  private final SelenideElement descriptionField = $("textarea.sc-lnDrWG.eaQaRY  ");

  private final SelenideElement addLabels = $("div.sc-gJfZKJ.kXGlCv  ");
  private final SelenideElement pacingDropdown = $("div.sc-iMrple.hVWLSP");

  private final SelenideElement totalDuration = $("div.sc-elrzoz.gxVJFo");
  private final SelenideElement totalReps = $("div.sc-gRPCMP.jSJKdj");
  private final SelenideElement minAccuracy = $("div.sc-JOTIl.fvWiZw");
  private final SelenideElement totalDurationText = $(".sc-gfvLdu.eANXID");
  private final SelenideElement totalRepsReqDText = $(".sc-cTWzIQ.fITlRr");
  private final SelenideElement minAccuracyText = $(".sc-iYorgH.gMxRTP");

  private final SelenideElement scenarioTab = $("div.sc-ufoUq.bLzUZt");
  private final SelenideElement challengeTab = $("div.sc-jnqsAJ.ekxfwz");
  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement filterButton = $("button.sc-OVAeL.gnQDxw");
  private final SelenideElement firstColumnScenario =
      $("th.sc-gKdcnr.bFbIWo.sc-ixTnoB.fEHupE");
  private final SelenideElement secondColumnScenario =
      $("th.sc-gKdcnr.dsnQry.sc-ixTnoB.fEHupE");
  private final SelenideElement firstColumnChallenge =
      $("th.sc-gKdcnr.bFbIWo.sc-dgcVsk.cOCTTo");
  private final SelenideElement secondColumnChallenge =
      $("th.sc-gKdcnr.dsnQry.sc-dgcVsk.cOCTTo");

  private final ElementsCollection scenariosChallengeItems = $$(".sc-doMyuq.fSKpVS");

  private final SelenideElement noContentImage = $(".sc-fusXuK.kHuqNT");
  private final SelenideElement noContentText = $(".sc-xIxYR.eUuvhb");
  private final SelenideElement noContentDescriptionText = $(".sc-crMJNM.czJprM");

  private final SelenideElement publishButton = $("button.sc-gSQGeZ.ihYKpI.primary");
  private final SelenideElement saveAsDraftButton = $("button.sc-gSQGeZ.YuVQS.inverse");
}
