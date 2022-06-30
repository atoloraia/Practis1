package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.component.AssignUsersModal;
import com.practis.web.selenide.component.CompanySelector;
import com.practis.web.selenide.component.ConfirmationPopUp;
import com.practis.web.selenide.component.Grid;
import com.practis.web.selenide.component.LabelChallenge;
import com.practis.web.selenide.component.LabelPanel;
import com.practis.web.selenide.component.LibraryTabs;
import com.practis.web.selenide.component.NavigationAdmin;
import com.practis.web.selenide.component.NavigationCompanies;
import com.practis.web.selenide.component.NewItemSelector;
import com.practis.web.selenide.component.PublishPractisSetPopUp;
import com.practis.web.selenide.component.Search;
import com.practis.web.selenide.component.Snackbar;

public class ComponentObjectFactory {

  private static CompanySelector COMPANY_SELECTOR;
  private static NewItemSelector NEW_ITEM_SELECTOR;
  private static Snackbar SNACKBAR;
  private static NavigationAdmin NAVIGATION;
  private static NavigationCompanies NAVIGATION_COMPANIES;
  private static Search SEARCH;
  private static Grid GRID;
  private static LabelPanel LABEL;
  private static LabelChallenge LABEL_CHALLENGE;
  private static LibraryTabs LIBRARY_CHALLENGE;
  private static ConfirmationPopUp DISCARD_CHANGES_POPUP;
  private static PublishPractisSetPopUp PUBLISH_PS_POPUP;
  private static AssignUsersModal ASSIGN_USERS_MODAL;


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
   * Create or return existing Navigation Admin.
   */
  public static NavigationAdmin navigation() {
    if (isNull(NAVIGATION)) {
      NAVIGATION = new NavigationAdmin();
    }
    return NAVIGATION;
  }

  /**
   * Create or return existing Navigation Companies.
   */
  public static NavigationCompanies navigationCompanies() {
    if (isNull(NAVIGATION_COMPANIES)) {
      NAVIGATION_COMPANIES = new NavigationCompanies();
    }
    return NAVIGATION_COMPANIES;
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

  /**
   * Create or return existing Label.
   */
  public static LabelPanel labelPanel() {
    if (isNull(LABEL)) {
      LABEL = new LabelPanel();
    }
    return LABEL;
  }

  /**
   * Create or return existing Label for Challenge.
   */
  public static LabelChallenge labelChallenge() {
    if (isNull(LABEL_CHALLENGE)) {
      LABEL_CHALLENGE = new LabelChallenge();
    }
    return LABEL_CHALLENGE;
  }

  /**
   * Create or return existing library tabs.
   */
  public static LibraryTabs libraryTabs() {
    if (isNull(LIBRARY_CHALLENGE)) {
      LIBRARY_CHALLENGE = new LibraryTabs();
    }
    return LIBRARY_CHALLENGE;
  }

  /**
   * Create or return existing Discard model.
   */
  public static ConfirmationPopUp discardChangeForm() {
    if (isNull(DISCARD_CHANGES_POPUP)) {
      DISCARD_CHANGES_POPUP = new ConfirmationPopUp();
    }
    return DISCARD_CHANGES_POPUP;
  }

  /**
   * Create or return existing Publish PS Pop up model.
   */
  public static PublishPractisSetPopUp publishPractisSetPopUp() {
    if (isNull(PUBLISH_PS_POPUP)) {
      PUBLISH_PS_POPUP = new PublishPractisSetPopUp();
    }
    return PUBLISH_PS_POPUP;
  }

  /**
   * Create or return existing 'Assign users to PS' Modal.
   */
  public static AssignUsersModal assignUsersModal() {
    if (isNull(ASSIGN_USERS_MODAL)) {
      ASSIGN_USERS_MODAL = new AssignUsersModal();
    }
    return ASSIGN_USERS_MODAL;
  }
}
