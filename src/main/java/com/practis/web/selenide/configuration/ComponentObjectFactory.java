package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.component.CompanySelector;
import com.practis.web.selenide.component.Grid;
import com.practis.web.selenide.component.Navigation;
import com.practis.web.selenide.component.NewItemSelector;
import com.practis.web.selenide.component.Search;
import com.practis.web.selenide.component.Snackbar;

public class ComponentObjectFactory {

  private static CompanySelector COMPANY_SELECTOR;
  private static NewItemSelector NEW_ITEM_SELECTOR;
  private static Snackbar SNACKBAR;
  private static Navigation NAVIGATION;
  private static Search SEARCH;
  private static Grid GRID;

  /**
   * Create or return existing CompanySelector.
   */
  public static CompanySelector companySelector() {
    if (isNull(COMPANY_SELECTOR)) {
      COMPANY_SELECTOR = new CompanySelector();
    }
    return COMPANY_SELECTOR;
  }

  /**
   * Create or return existing NewItemSelector.
   */
  public static NewItemSelector newItemSelector() {
    if (isNull(NEW_ITEM_SELECTOR)) {
      NEW_ITEM_SELECTOR = new NewItemSelector();
    }
    return NEW_ITEM_SELECTOR;
  }

  /**
   * Create or return existing Snackbar.
   */
  public static Snackbar snackbar() {
    if (isNull(SNACKBAR)) {
      SNACKBAR = new Snackbar();
    }
    return SNACKBAR;
  }

  /**
   * Create or return existing Navigation.
   */
  public static Navigation navigation() {
    if (isNull(NAVIGATION)) {
      NAVIGATION = new Navigation();
    }
    return NAVIGATION;
  }

  /**
   * Create or return existing Search.
   */
  public static Search search() {
    if (isNull(SEARCH)) {
      SEARCH = new Search();
    }
    return SEARCH;
  }

  /**
   * Create or return existing Grid.
   */
  public static Grid grid() {
    if (isNull(GRID)) {
      GRID = new Grid();
    }
    return GRID;
  }
}
