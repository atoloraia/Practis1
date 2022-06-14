package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.practis.web.selenide.component.GridRow;

public class ScenarioService {

  /**
   * Search scenario on grid by Scenario Title.
   */
  public GridRow searchScenario(final String name) {
    navigationCompanies().libraryNavigationItem.click();
    libraryTabs().goToTab("Scenarios");
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Click outside the scenario form.
   */
  public void exitScenarioWithDiscard() {
    jsClick(navigationCompanies().getProgressNavigationItem());
    discardChangeForm().discardChanges();
  }

  public void exitScenarioeWithSave() {
    jsClick(navigationCompanies().getProgressNavigationItem());
    discardChangeForm().saveChanges();
  }

}
