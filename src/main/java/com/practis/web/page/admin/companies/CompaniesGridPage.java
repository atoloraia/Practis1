package com.practis.web.page.admin.companies;

import static org.openqa.selenium.support.How.CSS;

import com.practis.web.page.common.PractisBasePage;
import java.util.List;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

class CompaniesGridPage extends PractisBasePage {

  @Autowired
  private AjaxElementLocatorFactory locatorFactory;

  @FindBy(how = CSS, css = "div[data-test='companyAccountsPage']")
  protected WebElement root;

  @FindBy(how = CSS, css = "div.sc-hgKjYe.jKMUGV")
  private WebElement titleElement;

  @FindBy(how = CSS, css = "div.sc-fydGIT.cNOJdV")
  private WebElement companySelectorElement;

  @FindBy(how = CSS, css = ".sc-bSqbRM.eeSYyN")
  private List<WebElement> companyUnderSelectorElements;

  @FindBy(how = CSS, css = "tr.sc-eoHYXO.geiMHT")
  private List<WebElement> companiesElement;


  @PostConstruct
  public void init() {
    PageFactory.initElements(locatorFactory, this);
  }
}
