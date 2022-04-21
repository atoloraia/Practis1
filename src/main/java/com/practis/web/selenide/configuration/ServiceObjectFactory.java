package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.web.selenide.service.AdminService;
import com.practis.web.selenide.service.CompanyService;

public class ServiceObjectFactory {

  private static AdminService ADMIN_SERVICE;
  private static CompanyService COMPANY_SERVICE;

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
}
