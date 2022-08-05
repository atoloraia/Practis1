package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.currentUserViewAdmin;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

public class CurrentUserAdminValidator {

  /**
   * Assert elements on Current User View + Dropdown.
   */
  public static void assertElementsOnCurrentAdminValidator() {
    currentUserViewAdmin().getUserAvatar().shouldBe(visible);
    currentUserViewAdmin().getUserName().shouldBe(visible);
    currentUserViewAdmin().getUserRole().shouldBe(visible);
    currentUserViewAdmin().getUserRole().shouldBe(exactText("Practis Admin"));
    currentUserViewAdmin().getUserAvatar().click();
    currentUserViewAdmin().getUserSelector().get(0).shouldBe(visible);
    currentUserViewAdmin().getUserSelector().get(0).shouldBe(exactText("My Settings"));
    currentUserViewAdmin().getUserSelector().get(1).shouldBe(visible);
    currentUserViewAdmin().getUserSelector().get(1).shouldBe(exactText("Log Out"));
    currentUserViewAdmin().getUserAvatar().click();
    companySelector().getCompanySelector().click();
    companySelector().getCompaniesUnderSelector().get(0).click();
  }

  /**
   * Assert elements on Current User View + Dropdown.
   */
  public static void assertElementsOnCurrentAdminValidatorCompany() {
    currentUserViewAdmin().getUserAvatar().shouldBe(visible);
    currentUserViewAdmin().getUserName().shouldBe(visible);
    currentUserViewAdmin().getUserRole().shouldBe(visible);
    currentUserViewAdmin().getUserRole().shouldBe(exactText("Practis Admin"));
    currentUserViewAdmin().getUserName().click();
    currentUserViewAdmin().getUserSelector().get(0).shouldBe(visible);
    currentUserViewAdmin().getUserSelector().get(0).shouldBe(exactText("Log Out"));
    currentUserViewAdmin().getUserSelector().get(1).shouldBe(visible);
    currentUserViewAdmin().getUserSelector().get(1).shouldBe(exactText("Company Settings"));
    currentUserViewAdmin().getUserSelector().get(2).shouldBe(visible);
    currentUserViewAdmin().getUserSelector().get(2).shouldBe(exactText("My Settings"));
    currentUserViewAdmin().getUserSelector().get(3).shouldBe(visible);
    currentUserViewAdmin().getUserSelector().get(3).shouldBe(exactText("Knowledge Base"));
    currentUserViewAdmin().getUserAvatar().click();
  }
}
