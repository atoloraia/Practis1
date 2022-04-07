package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.practis.utils.AwaitUtils.awaitSoft;
import static java.lang.String.format;
import static org.openqa.selenium.support.How.CSS;

import com.codeborne.selenide.SelenideElement;
import com.practis.configuration.selenium.support.PractisPage;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@Getter
public class CompanySelector {

  private final SelenideElement companySelector = $("div[data-test='companyDropDownToggleButton']");
  private final SelenideElement companiesUnderSelector = $("div[data-test*='company_']");

}
