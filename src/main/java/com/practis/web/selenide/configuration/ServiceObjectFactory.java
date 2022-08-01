package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.service.AdminService;
import com.practis.web.selenide.service.CompanyService;
import com.practis.web.selenide.service.LoginService;
import com.practis.web.selenide.service.company.ChallengeService;
import com.practis.web.selenide.service.company.LabelPanelService;
import com.practis.web.selenide.service.company.PractisSetService;
import com.practis.web.selenide.service.company.ScenarioService;
import com.practis.web.selenide.service.company.TeamsService;
import com.practis.web.selenide.service.company.user.InviteUserService;
import com.practis.web.selenide.service.company.user.LabelService;
import com.practis.web.selenide.service.company.user.TeamService;
import com.practis.web.selenide.service.component.SaveAsDraftPopUpService;

public class ServiceObjectFactory {

  private static AdminService ADMIN_SERVICE;
  private static CompanyService COMPANY_SERVICE;
  private static TeamsService TEAMS_SERVICE;
  private static ChallengeService CHALLENGE_SERVICE;
  private static ScenarioService SCENARIO_SERVICE;
  private static PractisSetService PRACTIS_SET_SERVICE;
  private static LabelPanelService LABEL_SERVICE;
  private static LoginService LOGIN_SERVICE;
  private static InviteUserService INVITE_USER_SERVICE;
  private static TeamService INVITE_USER_TEAM_SERVICE;
  private static LabelService INVITE_USER_LABEL_SERVICE;
  private static SaveAsDraftPopUpService SAVE_AS_DRAFT_SERVICE;

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
   * Create or return existing TeamsService.
   */
  public static TeamsService teamsService() {
    if (isNull(TEAMS_SERVICE)) {
      TEAMS_SERVICE = new TeamsService();
    }
    return TEAMS_SERVICE;
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
   * Create or return existing Invite Service.
   */
  public static InviteUserService userService() {
    if (isNull(INVITE_USER_SERVICE)) {
      INVITE_USER_SERVICE = new InviteUserService();
    }
    return INVITE_USER_SERVICE;
  }

  /**
   * Create or return existing Invite:Team Module Service.
   */
  public static TeamService teamService() {
    if (isNull(INVITE_USER_TEAM_SERVICE)) {
      INVITE_USER_TEAM_SERVICE = new TeamService();
    }
    return INVITE_USER_TEAM_SERVICE;
  }

  /**
   * Create or return existing Invite:Label Module Service.
   */
  public static LabelService labelService() {
    if (isNull(INVITE_USER_LABEL_SERVICE)) {
      INVITE_USER_LABEL_SERVICE = new LabelService();
    }
    return INVITE_USER_LABEL_SERVICE;
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
}
