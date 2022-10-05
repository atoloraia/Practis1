package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.bottomProfileMenu;

public class BottomProfileMenuValidator {

  /**
   * Assert elements on Admin Bottom Profile Menu.
   */
  public static void assertElementsOnAdminBottomProfileMenu() {
    bottomProfileMenu().getUserAvatar().shouldBe(visible);
    bottomProfileMenu().getUserName().shouldBe(visible);
    bottomProfileMenu().getUserRole().shouldBe(visible);
    bottomProfileMenu().getUserRole().shouldBe(exactText("Practis Admin"));
  }

  /**
   * Assert elements on Admin Bottom Profile Menu Dropdown.
   */
  public static void assertElementsOnAdminBottomProfileDropdown() {
    bottomProfileMenu().getUserSelector().get(0).shouldBe(visible);
    bottomProfileMenu().getUserSelector().get(0).shouldBe(exactText("My Settings"));
    bottomProfileMenu().getUserSelector().get(1).shouldBe(visible);
    bottomProfileMenu().getUserSelector().get(1).shouldBe(exactText("Log Out"));
  }

  /**
   * Assert elements on Bottom Profile Menu.
   */
  public static void assertElementsOnBottomProfileMenu() {
    bottomProfileMenu().getUserAvatar().shouldBe(visible);
    bottomProfileMenu().getUserName().shouldBe(visible);
    bottomProfileMenu().getUserRole().shouldBe(visible);
    bottomProfileMenu().getUserRole().shouldBe(exactText("Practis Admin"));
  }

  /**
   * Assert elements on Bottom Profile Menu Dropdown.
   */
  public static void assertElementsOnBottomProfileDropdown() {
    bottomProfileMenu().getUserSelector().get(0).shouldBe(visible);
    bottomProfileMenu().getUserSelector().get(0).shouldBe(exactText("Log Out"));
    bottomProfileMenu().getUserSelector().get(1).shouldBe(visible);
    bottomProfileMenu().getUserSelector().get(1).shouldBe(exactText("Company Settings"));
    bottomProfileMenu().getUserSelector().get(2).shouldBe(visible);
    bottomProfileMenu().getUserSelector().get(2).shouldBe(exactText("My Settings"));
  }

}
