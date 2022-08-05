package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.component.AreYouSurePopUp;
import com.practis.web.selenide.component.AssignUsersModule;
import com.practis.web.selenide.component.CompanySelector;
import com.practis.web.selenide.component.CurrentUserViewAdmin;
import com.practis.web.selenide.component.Grid;
import com.practis.web.selenide.component.LabelPanel;
import com.practis.web.selenide.component.LibraryTabs;
import com.practis.web.selenide.component.NavigationAdmin;
import com.practis.web.selenide.component.NavigationCompanies;
import com.practis.web.selenide.component.NewItemSelector;
import com.practis.web.selenide.component.PacingDropdown;
import com.practis.web.selenide.component.PublishPractisSetPopUp;
import com.practis.web.selenide.component.ScenarioConfirmationPopUp;
import com.practis.web.selenide.component.Search;
import com.practis.web.selenide.component.Snackbar;
import com.practis.web.selenide.component.selection.LabelModule;
import com.practis.web.selenide.component.selection.ScenarioModule;
import com.practis.web.selenide.component.selection.StatusModule;
import com.practis.web.selenide.component.selection.TeamModule;
import com.practis.web.selenide.component.user.invite.InviteUserPsModule;
import com.practis.web.selenide.component.user.invite.InviteUserRoleModule;
import com.practis.web.selenide.component.user.invite.SaveAsDraftPopUp;
import com.practis.web.selenide.component.user.invite.UnsavedProgressPopUp;

public class ComponentObjectFactory {

  private static CompanySelector COMPANY_SELECTOR;
  private static NewItemSelector NEW_ITEM_SELECTOR;
  private static Snackbar SNACKBAR;
  private static NavigationAdmin NAVIGATION;
  private static NavigationCompanies NAVIGATION_COMPANIES;
  private static Search SEARCH;
  private static Grid GRID;
  private static LabelPanel LABEL;
  private static LibraryTabs LIBRARY_CHALLENGE;
  private static ScenarioConfirmationPopUp SCENARIO_CONFIRMATION_POPUP;
  private static PublishPractisSetPopUp PUBLISH_PS_POPUP;
  private static AssignUsersModule ASSIGN_USERS_MODULE;
  private static NavigationAdmin NAVIGATION_ADMIN;
  private static TeamModule INVITE_USER_TEAM_MODULE;
  private static InviteUserRoleModule INVITE_USER_ROLE_MODULE;
  private static InviteUserPsModule INVITE_USER_PS_MODULE;
  private static LabelModule INVITE_USER_LABEL_MODULE;
  private static StatusModule LIBRARY_STATUS_MODULE;
  private static ScenarioModule FEED_SCENARIO_MODULE;
  private static AreYouSurePopUp ARE_YOU_SURE_POPUP;
  private static SaveAsDraftPopUp SAVE_AS_DRAFT_POPUP;
  private static UnsavedProgressPopUp UNSAVED_PROGRESS_POPUP;
  private static PacingDropdown PACING_DROPDOWN;
  private static CurrentUserViewAdmin CURRENT_USER_VIEW;


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
   * Create or return existing Pacing.
   */
  public static PacingDropdown pacingDropdown() {
    if (isNull(PACING_DROPDOWN)) {
      PACING_DROPDOWN = new PacingDropdown();
    }
    return PACING_DROPDOWN;
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
   * Create or return existing library tabs.
   */
  public static LibraryTabs libraryTabs() {
    if (isNull(LIBRARY_CHALLENGE)) {
      LIBRARY_CHALLENGE = new LibraryTabs();
    }
    return LIBRARY_CHALLENGE;
  }

  /**
   * Create or return existing Scenario Discard model.
   */
  public static ScenarioConfirmationPopUp scenarioConfirmationPopUp() {
    if (isNull(SCENARIO_CONFIRMATION_POPUP)) {
      SCENARIO_CONFIRMATION_POPUP = new ScenarioConfirmationPopUp();
    }
    return SCENARIO_CONFIRMATION_POPUP;
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
   * Create or return existing 'Assign users to PS' Module.
   */
  public static AssignUsersModule assignUsersModule() {
    if (isNull(ASSIGN_USERS_MODULE)) {
      ASSIGN_USERS_MODULE = new AssignUsersModule();
    }
    return ASSIGN_USERS_MODULE;
  }

  /**
   * Create or return existing 'Navigation Admin Panel.
   */
  public static NavigationAdmin navigationAdminSideBar() {
    if (isNull(NAVIGATION_ADMIN)) {
      NAVIGATION_ADMIN = new NavigationAdmin();
    }
    return NAVIGATION_ADMIN;
  }

  /**
   * Create or return existing 'CurrentUser View - Asmin'.
   */
  public static CurrentUserViewAdmin currentUserViewAdmin() {
    if (isNull(CURRENT_USER_VIEW)) {
      CURRENT_USER_VIEW = new CurrentUserViewAdmin();
    }
    return CURRENT_USER_VIEW;
  }

  /**
   * Create or return existing Invite User: Team Module.
   */
  public static TeamModule teamModule() {
    if (isNull(INVITE_USER_TEAM_MODULE)) {
      INVITE_USER_TEAM_MODULE = new TeamModule();
    }
    return INVITE_USER_TEAM_MODULE;
  }

  /**
   * Create or return existing Invite User: Role Module.
   */
  public static InviteUserRoleModule inviteUserRoleModule() {
    if (isNull(INVITE_USER_ROLE_MODULE)) {
      INVITE_USER_ROLE_MODULE = new InviteUserRoleModule();
    }
    return INVITE_USER_ROLE_MODULE;
  }

  /**
   * Create or return existing Invite User: PS Module.
   */
  public static InviteUserPsModule inviteUserPsModule() {
    if (isNull(INVITE_USER_PS_MODULE)) {
      INVITE_USER_PS_MODULE = new InviteUserPsModule();
    }
    return INVITE_USER_PS_MODULE;
  }

  /**
   * Create or return existing Invite User: Label Module.
   */
  public static LabelModule labelModule() {
    if (isNull(INVITE_USER_LABEL_MODULE)) {
      INVITE_USER_LABEL_MODULE = new LabelModule();
    }
    return INVITE_USER_LABEL_MODULE;
  }

  /**
   * Return existing Feed: Scenario Module.
   */
  public static ScenarioModule scenarioModule() {
    if (isNull(FEED_SCENARIO_MODULE)) {
      FEED_SCENARIO_MODULE = new ScenarioModule();
    }
    return FEED_SCENARIO_MODULE;
  }

  /**
   * Return existing Library: Status Module.
   */
  public static StatusModule statusModule() {
    if (isNull(LIBRARY_STATUS_MODULE)) {
      LIBRARY_STATUS_MODULE = new StatusModule();
    }
    return LIBRARY_STATUS_MODULE;
  }

  /**
   * Create or return existing Are You Sure popup.
   */
  public static AreYouSurePopUp areYouSurePopUp() {
    if (isNull(ARE_YOU_SURE_POPUP)) {
      ARE_YOU_SURE_POPUP = new AreYouSurePopUp();
    }
    return ARE_YOU_SURE_POPUP;
  }

  /**
   * Create or return existing Are You Sure popup.
   */
  public static SaveAsDraftPopUp saveAsDraftPopUp() {
    if (isNull(SAVE_AS_DRAFT_POPUP)) {
      SAVE_AS_DRAFT_POPUP = new SaveAsDraftPopUp();
    }
    return SAVE_AS_DRAFT_POPUP;
  }

  /**
   * Create or return existing Unsaved Progress popup.
   */
  public static UnsavedProgressPopUp unsavedProgressPopUp() {
    if (isNull(UNSAVED_PROGRESS_POPUP)) {
      UNSAVED_PROGRESS_POPUP = new UnsavedProgressPopUp();
    }
    return UNSAVED_PROGRESS_POPUP;
  }
}
