package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.service.AdminService;

public class ServiceObjectFactory {

  private static AdminService ADMIN_SERVICE;

  /**
   * Create or return existing AdminService.
   */
  public static AdminService admin() {
    if (isNull(ADMIN_SERVICE)) {
      ADMIN_SERVICE = new AdminService();
    }
    return ADMIN_SERVICE;
  }
}
