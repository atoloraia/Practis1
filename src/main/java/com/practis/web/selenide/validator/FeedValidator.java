package com.practis.web.selenide.validator;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminEditPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyEditPage;
import static java.util.Locale.ROOT;

import com.practis.dto.NewAdminInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.admin.AdminEditPage;
import com.practis.web.selenide.page.company.FeedPage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedValidator {


  /**
   * Assert elements on Feed Page.
   */
  public static void assertElementsOnUserSettingsPage() {

    new FeedPage().getFeedButton().shouldBe(visible);

  }

}
