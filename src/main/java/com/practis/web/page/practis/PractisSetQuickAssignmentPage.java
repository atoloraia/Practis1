package com.practis.web.page.practis;

import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PractisPage
@RequiredArgsConstructor
public class PractisSetQuickAssignmentPage {

  private static final int HEADER_WAIT_TIMEOUT = 5;

  @FindBy(how = CSS, css = "div.sc-NgwtQ.fqZbvY")
  private WebElement header;

  /**
   * To be added.
   */
  public String getHeader() {
    return header.getText();
  }
}
