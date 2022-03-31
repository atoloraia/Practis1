package com.practis.web.page.admin;

import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
public class CompanyEditPage {

  @FindBy(how = CSS, css = "div.sc-cZrtSY.jbVUOO")
  private WebElement name;

  @FindBy(how = CSS, css = "input[name=\"email\"]")
  private WebElement companyEmail;

  public String getCompanyName() {
    return name.getText();
  }

  public String getEmail() {
    return companyEmail.getAttribute("value");
  }
}
