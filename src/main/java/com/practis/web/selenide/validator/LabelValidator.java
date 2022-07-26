package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelPanel;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelPanelService;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LabelValidator {

  /**
   * Assert elements on Empty Label panel.
   */
  public static void assertElementsEmptyLabelPanel() {
    labelPanel().getExpandLabelIcon().shouldBe(visible);
    labelPanelService().openPanel();

    labelPanel().getCollapseLabelIcon().shouldBe(visible);
    labelPanel().getSearchLabelField().shouldBe(visible);

    labelPanel().getFilterByText().shouldBe(visible);
    labelPanel().getFilterByText().shouldBe(exactText("Filter by"));

    labelPanel().getNoLabelsSelectedText().shouldBe(exactText("No Labels selected"));

    labelPanel().getClearLabelsButton().shouldBe(visible);
    labelPanel().getClearLabelsButton().shouldBe(exactText("Clear"));

    labelPanel().getAddLabelLink().shouldBe(exactText("+\n"
        + "Add Label"));

    labelPanelService().clickAddLabel();
    labelPanel().getLabelInput().shouldBe(visible);
    labelPanel().getLabelInput().shouldBe(attribute("placeholder", "Label name"));

    labelPanel().getCancelButton().shouldBe(visible);
    labelPanel().getCancelButton().shouldBe(exactText("Cancel"));

    labelPanel().getSaveButton().shouldBe(visible);
    labelPanel().getSaveButton().shouldBe(exactText("Save"));
  }

  /**
   * Assert elements on Label panel.
   */
  public static void assertElementsLabelPanel() {

    labelPanel().getCollapseLabelIcon().shouldBe(visible);
    labelPanel().getSearchLabelField().shouldBe(visible);

    labelPanel().getFilterByText().shouldBe(visible);
    labelPanel().getFilterByText().shouldBe(exactText("Filter by"));

    labelPanel().getNoLabelsSelectedText().shouldBe(exactText("No Labels selected"));

    labelPanel().getClearLabelsButton().shouldBe(visible);
    labelPanel().getClearLabelsButton().shouldBe(exactText("Clear"));

    labelPanel().getLabelRow().get(0).shouldBe(visible);

    labelPanel().getAddLabelLink().lastChild().shouldBe(exactText("+\n"
        + "Add Label"));

  }

}
