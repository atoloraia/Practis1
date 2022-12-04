package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.component.selection.AssignPractisSetsAndDueDatesModule;
import com.practis.web.selenide.service.AddMobileNumberService;
import com.practis.web.selenide.service.LoginService;
import com.practis.web.selenide.service.admin.AdminService;
import com.practis.web.selenide.service.admin.CompanyService;
import com.practis.web.selenide.service.company.ChallengeService;
import com.practis.web.selenide.service.company.InviteUserService;
import com.practis.web.selenide.service.company.LabelPanelService;
import com.practis.web.selenide.service.company.NavigationCompanyService;
import com.practis.web.selenide.service.company.PractisSetService;
import com.practis.web.selenide.service.company.ScenarioService;
import com.practis.web.selenide.service.company.team.CreateTeamsService;
import com.practis.web.selenide.service.company.team.ManageTeamService;
import com.practis.web.selenide.service.company.team.MembersTabService;
import com.practis.web.selenide.service.company.team.TeamsPageService;
import com.practis.web.selenide.service.company.team.TrainingTabService;
import com.practis.web.selenide.service.popup.SaveAsDraftPopUpService;
import com.practis.web.selenide.service.popup.UnsavedProgressPopUpService;
import com.practis.web.selenide.service.selection.AssignModuleService;
import com.practis.web.selenide.service.selection.LabelSelectionService;
import com.practis.web.selenide.service.selection.PractisSetSelectionService;
import com.practis.web.selenide.service.selection.RoleUserModuleService;
import com.practis.web.selenide.service.selection.TeamSelectionService;


public class ServiceObjectFactory {

  private static NavigationCompanyService NAVIGATION_COMPANY_SERVICE;
  private static AdminService ADMIN_SERVICE;
  private static CompanyService COMPANY_SERVICE;
  private static CreateTeamsService TEAMS_SERVICE;
  private static ManageTeamService MANAGE_TEAM_SERVICE;
  private static MembersTabService MEMBERS_TAB_SERVICE;
  private static TrainingTabService TRAINING_TAB_SERVICE;
  private static TeamsPageService TEAM_PAGE_SERVICE;
  private static ChallengeService CHALLENGE_SERVICE;
  private static ScenarioService SCENARIO_SERVICE;
  private static PractisSetService PRACTIS_SET_SERVICE;
  private static LabelPanelService LABEL_SERVICE;
  private static LoginService LOGIN_SERVICE;
  private static AddMobileNumberService ADD_MOBILE_SERVICE;
  private static InviteUserService INVITE_USER_SERVICE;
  private static TeamSelectionService INVITE_USER_TEAM_SERVICE;
  private static PractisSetSelectionService INVITE_USER_PRACTIS_SET_SERVICE;
  private static LabelSelectionService INVITE_USER_LABEL_SERVICE;
  private static RoleUserModuleService INVITE_USER_ROLE_SERVICE;
  private static AssignModuleService ASSIGN_USER_MODULE_SERVICE;
  private static SaveAsDraftPopUpService SAVE_AS_DRAFT_SERVICE;
  private static UnsavedProgressPopUpService UNSAVED_PROGRESS_SERVICE;
  private static AssignPractisSetsAndDueDatesModule ASSIGN_PRACTIS_SET_SERVICE;

  //Teams
  /**
   * Create or return existing Create Teams Service.
   */
  public static NavigationCompanyService navigationCompanyService() {
    if (isNull(NAVIGATION_COMPANY_SERVICE)) {
      NAVIGATION_COMPANY_SERVICE = new NavigationCompanyService();
    }
    return NAVIGATION_COMPANY_SERVICE;
  }

  /**
   * Create or return existing Create Teams Service.
   */
  public static CreateTeamsService createTeamsService() {
    if (isNull(TEAMS_SERVICE)) {
      TEAMS_SERVICE = new CreateTeamsService();
    }
    return TEAMS_SERVICE;
  }

  /**
   * Create or return existing ManageTeamService.
   */
  public static ManageTeamService manageTeamService() {
    if (isNull(MANAGE_TEAM_SERVICE)) {
      MANAGE_TEAM_SERVICE = new ManageTeamService();
    }
    return MANAGE_TEAM_SERVICE;
  }

  /**
   * Create or return existing ManageTeamService.
   */
  public static MembersTabService membersTabService() {
    if (isNull(MEMBERS_TAB_SERVICE)) {
      MEMBERS_TAB_SERVICE = new MembersTabService();
    }
    return MEMBERS_TAB_SERVICE;
  }

  /**
   * Create or return existing ManageTeamService.
   */
  public static TrainingTabService trainingTabService() {
    if (isNull(TRAINING_TAB_SERVICE)) {
      TRAINING_TAB_SERVICE = new TrainingTabService();
    }
    return TRAINING_TAB_SERVICE;
  }

  /**
   * Create or return existing TeamsPageService.
   */
  public static TeamsPageService teamsPageService() {
    if (isNull(TEAM_PAGE_SERVICE)) {
      TEAM_PAGE_SERVICE = new TeamsPageService();
    }
    return TEAM_PAGE_SERVICE;
  }

  /**
   * Create or return existing TeamSelectionService.
   */
  public static TeamSelectionService teamModuleService() {
    if (isNull(INVITE_USER_TEAM_SERVICE)) {
      INVITE_USER_TEAM_SERVICE = new TeamSelectionService();
    }
    return INVITE_USER_TEAM_SERVICE;
  }

  /**
   * Create or return existing AdminService.
   */
  public static AdminService adminService() {
    if (isNull(ADMIN_SERVICE)) {
      ADMIN_SERVICE = new AdminService();
    }
    return ADMIN_SERVICE;
  }

  /**
   * Create or return existing CompanyService.
   */
  public static CompanyService companyService() {
    if (isNull(COMPANY_SERVICE)) {
      COMPANY_SERVICE = new CompanyService();
    }
    return COMPANY_SERVICE;
  }

  /**
   * Create or return existing ChallengeService.
   */
  public static ChallengeService challengeService() {
    if (isNull(CHALLENGE_SERVICE)) {
      CHALLENGE_SERVICE = new ChallengeService();
    }
    return CHALLENGE_SERVICE;
  }

  /**
   * Create or return existing ScenarioService.
   */
  public static ScenarioService scenarioService() {
    if (isNull(SCENARIO_SERVICE)) {
      SCENARIO_SERVICE = new ScenarioService();
    }
    return SCENARIO_SERVICE;
  }

  /**
   * Create or return existing ScenarioService.
   */
  public static PractisSetService practisSetService() {
    if (isNull(PRACTIS_SET_SERVICE)) {
      PRACTIS_SET_SERVICE = new PractisSetService();
    }
    return PRACTIS_SET_SERVICE;
  }

  /**
   * Create or return existing Labels Service.
   */
  public static LabelPanelService labelPanelService() {
    if (isNull(LABEL_SERVICE)) {
      LABEL_SERVICE = new LabelPanelService();
    }
    return LABEL_SERVICE;
  }

  /**
   * Create or return existing Login Service.
   */
  public static LoginService loginService() {
    if (isNull(LOGIN_SERVICE)) {
      LOGIN_SERVICE = new LoginService();
    }
    return LOGIN_SERVICE;
  }


  /**
   * Create or return existing Add Mobile Number Service.
   */
  public static AddMobileNumberService addMobileService() {
    if (isNull(ADD_MOBILE_SERVICE)) {
      ADD_MOBILE_SERVICE = new AddMobileNumberService();
    }
    return ADD_MOBILE_SERVICE;
  }

  /**
   * Create or return existing Invite Service.
   */
  public static InviteUserService userService() {
    if (isNull(INVITE_USER_SERVICE)) {
      INVITE_USER_SERVICE = new InviteUserService();
    }
    return INVITE_USER_SERVICE;
  }

  /**
   * Create or return existing Invite: Practis Set Module Service.
   */
  public static PractisSetSelectionService psModuleService() {
    if (isNull(INVITE_USER_PRACTIS_SET_SERVICE)) {
      INVITE_USER_PRACTIS_SET_SERVICE = new PractisSetSelectionService();
    }
    return INVITE_USER_PRACTIS_SET_SERVICE;
  }

  /**
   * Create or return existing Invite:Label Module Service.
   */
  public static LabelSelectionService labelModuleService() {
    if (isNull(INVITE_USER_LABEL_SERVICE)) {
      INVITE_USER_LABEL_SERVICE = new LabelSelectionService();
    }
    return INVITE_USER_LABEL_SERVICE;
  }

  /**
   * Create or return existing Invite:Assign: Role Service.
   */
  public static RoleUserModuleService roleModuleService() {
    if (isNull(INVITE_USER_ROLE_SERVICE)) {
      INVITE_USER_ROLE_SERVICE = new RoleUserModuleService();
    }
    return INVITE_USER_ROLE_SERVICE;
  }

  /**
   * Create or return existing Invite:Assign Module Service.
   */
  public static AssignModuleService assignUserModuleService() {
    if (isNull(ASSIGN_USER_MODULE_SERVICE)) {
      ASSIGN_USER_MODULE_SERVICE = new AssignModuleService();
    }
    return ASSIGN_USER_MODULE_SERVICE;
  }

  /**
   * Create or return existing Save as Draft pop-up.
   */
  public static SaveAsDraftPopUpService saveAsDraftService() {
    if (isNull(SAVE_AS_DRAFT_SERVICE)) {
      SAVE_AS_DRAFT_SERVICE = new SaveAsDraftPopUpService();
    }
    return SAVE_AS_DRAFT_SERVICE;
  }

  /**
   * Create or return existing Unsaved Progress pop-up.
   */
  public static UnsavedProgressPopUpService unsavedProgressPopUpService() {
    if (isNull(UNSAVED_PROGRESS_SERVICE)) {
      UNSAVED_PROGRESS_SERVICE = new UnsavedProgressPopUpService();
    }
    return UNSAVED_PROGRESS_SERVICE;
  }

  /**
   * Create or return existing ASSIGN PRACTIS SET pop-up.
   */
  public static AssignPractisSetsAndDueDatesModule assignPractisSetsAndDueDatesModule() {
    if (isNull(ASSIGN_PRACTIS_SET_SERVICE)) {
      ASSIGN_PRACTIS_SET_SERVICE = new AssignPractisSetsAndDueDatesModule();
    }
    return ASSIGN_PRACTIS_SET_SERVICE;
  }
}
