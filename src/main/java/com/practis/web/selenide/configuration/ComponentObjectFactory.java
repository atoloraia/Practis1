package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.component.AreYouSurePopUp;
import com.practis.web.selenide.component.AssignUsersModal;
import com.practis.web.selenide.component.CompanySelector;
import com.practis.web.selenide.component.Grid;
import com.practis.web.selenide.component.LabelPanel;
import com.practis.web.selenide.component.LibraryTabs;
import com.practis.web.selenide.component.NavigationAdmin;
import com.practis.web.selenide.component.NavigationCompanies;
import com.practis.web.selenide.component.NewItemSelector;
import com.practis.web.selenide.component.PsConfirmationPopUp;
import com.practis.web.selenide.component.PublishPractisSetPopUp;
import com.practis.web.selenide.component.ScenarioConfirmationPopUp;
import com.practis.web.selenide.component.Search;
import com.practis.web.selenide.component.Snackbar;
import com.practis.web.selenide.component.user.InviteUserTeamModal;
import com.practis.web.selenide.component.user.invite.InviteUserLabelModel;
import com.practis.web.selenide.component.user.invite.InviteUserPsModel;
import com.practis.web.selenide.component.user.invite.InviteUserRoleModel;

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
  private static PsConfirmationPopUp PS_CONFIRMATION_POPUP;
  private static ScenarioConfirmationPopUp SCENARIO_CONFIRMATION_POPUP;
  private static PublishPractisSetPopUp PUBLISH_PS_POPUP;
  private static AssignUsersModal ASSIGN_USERS_MODAL;
  private static NavigationAdmin NAVIGATION_ADMIN;
  private static InviteUserTeamModal INVITE_USER_TEAM_MODAL;
  private static InviteUserRoleModel INVITE_USER_ROLE_MODAL;
  private static InviteUserPsModel INVITE_USER_PS_MODAL;
  private static InviteUserLabelModel INVITE_USER_LABEL_MODAL;
  private static AreYouSurePopUp ARE_YOU_SURE_POPUP;


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
   * Create or return existing library tabs.
   */
  public static LibraryTabs libraryTabs() {
    if (isNull(LIBRARY_CHALLENGE)) {
      LIBRARY_CHALLENGE = new LibraryTabs();
    }
    return LIBRARY_CHALLENGE;
  }

  /**
   * Create or return existing Practis Set Discard model.
   */
  public static PsConfirmationPopUp psConfirmationPopUp() {
    if (isNull(PS_CONFIRMATION_POPUP)) {
      PS_CONFIRMATION_POPUP = new PsConfirmationPopUp();
    }
    return PS_CONFIRMATION_POPUP;
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
   * Create or return existing 'Assign users to PS' Modal.
   */
  public static AssignUsersModal assignUsersModal() {
    if (isNull(ASSIGN_USERS_MODAL)) {
      ASSIGN_USERS_MODAL = new AssignUsersModal();
    }
    return ASSIGN_USERS_MODAL;
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
   * Create or return existing Invite User: Team Modal.
   */
  public static InviteUserTeamModal inviteUserTeamModal() {
    if (isNull(INVITE_USER_TEAM_MODAL)) {
      INVITE_USER_TEAM_MODAL = new InviteUserTeamModal();
    }
    return INVITE_USER_TEAM_MODAL;
  }

  /**
   * Create or return existing Invite User: Role Modal.
   */
  public static InviteUserRoleModel inviteUserRoleModel() {
    if (isNull(INVITE_USER_ROLE_MODAL)) {
      INVITE_USER_ROLE_MODAL = new InviteUserRoleModel();
    }
    return INVITE_USER_ROLE_MODAL;
  }

  /**
   * Create or return existing Invite User: PS Modal.
   */
  public static InviteUserPsModel inviteUserPsModel() {
    if (isNull(INVITE_USER_PS_MODAL)) {
      INVITE_USER_PS_MODAL = new InviteUserPsModel();
    }
    return INVITE_USER_PS_MODAL;
  }

  /**
   * Create or return existing Invite User: Label Modal.
   */
  public static InviteUserLabelModel inviteUserLabelModel() {
    if (isNull(INVITE_USER_LABEL_MODAL)) {
      INVITE_USER_LABEL_MODAL = new InviteUserLabelModel();
    }
    return INVITE_USER_LABEL_MODAL;
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
}
