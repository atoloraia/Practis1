package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.service.AdminService;
import com.practis.web.selenide.service.CompanyService;
import com.practis.web.selenide.service.LoginService;
import com.practis.web.selenide.service.company.ChallengeService;
import com.practis.web.selenide.service.company.InviteUserService;
import com.practis.web.selenide.service.company.LabelPanelService;
import com.practis.web.selenide.service.company.PractisSetService;
import com.practis.web.selenide.service.company.ScenarioService;
import com.practis.web.selenide.service.company.TeamsService;

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

  /**
   * Create or return existing AdminService.
   */
  public static AdminService admin() {
    if (isNull(ADMIN_SERVICE)) {
      ADMIN_SERVICE = new AdminService();
    }
    return ADMIN_SERVICE;
  }

  /**
   * Create or return existing CompanyService.
   */
  public static CompanyService company() {
    if (isNull(COMPANY_SERVICE)) {
      COMPANY_SERVICE = new CompanyService();
    }
    return COMPANY_SERVICE;
  }

  /**
   * Create or return existing TeamsService.
   */
  public static TeamsService teams() {
    if (isNull(TEAMS_SERVICE)) {
      TEAMS_SERVICE = new TeamsService();
    }
    return TEAMS_SERVICE;
  }

  /**
   * Create or return existing ChallengeService.
   */
  public static ChallengeService challenge() {
    if (isNull(CHALLENGE_SERVICE)) {
      CHALLENGE_SERVICE = new ChallengeService();
    }
    return CHALLENGE_SERVICE;
  }

  /**
   * Create or return existing ScenarioService.
   */
  public static ScenarioService scenario() {
    if (isNull(SCENARIO_SERVICE)) {
      SCENARIO_SERVICE = new ScenarioService();
    }
    return SCENARIO_SERVICE;
  }

  /**
   * Create or return existing ScenarioService.
   */
  public static PractisSetService practisSet() {
    if (isNull(PRACTIS_SET_SERVICE)) {
      PRACTIS_SET_SERVICE = new PractisSetService();
    }
    return PRACTIS_SET_SERVICE;
  }

  /**
   * Create or return existing Labels Service.
   */
  public static LabelPanelService label() {
    if (isNull(LABEL_SERVICE)) {
      LABEL_SERVICE = new LabelPanelService();
    }
    return LABEL_SERVICE;
  }

  /**
   * Create or return existing Login Service.
   */
  public static LoginService login() {
    if (isNull(LOGIN_SERVICE)) {
      LOGIN_SERVICE = new LoginService();
    }
    return LOGIN_SERVICE;
  }

  /**
   * Create or return existing Invite Service.
   */
  public static InviteUserService user() {
    if (isNull(INVITE_USER_SERVICE)) {
      INVITE_USER_SERVICE = new InviteUserService();
    }
    return INVITE_USER_SERVICE;
  }

}
