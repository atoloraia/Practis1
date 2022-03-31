package com.practis.web.page.admin.companies;

import static com.practis.utils.AwaitUtils.awaitSoft;

import com.practis.configuration.selenium.support.PractisPage;

@PractisPage
public class CompaniesGridAssertPage extends CompaniesGridActionPage {

  public boolean isOnPage() {
    awaitSoft(3, () -> root.isDisplayed());
    return root.isDisplayed();
  }
}
