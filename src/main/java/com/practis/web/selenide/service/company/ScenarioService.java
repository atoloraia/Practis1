package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;

import com.practis.web.selenide.component.GridRow;

public class ScenarioService {

  /**
   * Search scenario on grid by Scenario Title.
   */
  public GridRow searchScenario(final String name) {
    navigationCompanies().goTo("Library");
    libraryTabs().goToTab("Scenarios");
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Click outside the scenario form.
   */
  public void exitScenarioWithDiscard() {
    navigationCompanies().goTo("Progress");
    discardChangeForm().discardChanges();
  }

  public void exitScenarioeWithSave() {
    navigationCompanies().goTo("Progress");
    discardChangeForm().saveChanges();
  }

}
