package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static java.lang.Thread.sleep;

import com.practis.dto.NewCompanyInput;
import com.practis.web.selenide.component.GridRow;
import lombok.SneakyThrows;

public class CompanyService {

    /** Fill create Company form. */
    public void fillCreateCompanyForm(final NewCompanyInput input, final int rowNum) {
        companyCreatePage().getCompanyNameField().get(rowNum).sendKeys(input.getName());
        companyCreatePage().getCompanyEmailField().get(rowNum).sendKeys(input.getEmail());
        companyCreatePage().getFirstNameField().get(rowNum).sendKeys(input.getFirstName());
        companyCreatePage().getLastNameField().get(rowNum).sendKeys(input.getLastName());
    }

    /** Click '+ Add another' button. */
    public void addRow() {
        companyCreatePage().getAddRowLink().click();
    }

    /** Click 'Delete' button. */
    public void deleteRow(final int rowNum) {
        companyCreatePage().getDeleteRowButton().get(rowNum).click();
    }

    /** Click 'Invite' button. */
    @SneakyThrows
    public void clickInvite() {
        sleep(2000);
        companyCreatePage().getInviteButton().click();
    }

    /** Search admin on grid by email. */
    public GridRow searchCompany(final String name) {
        navigationAdmin().companyNavigationItem.click();
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Fill Company form and click 'Invite' button. */
    public void createCompany(final NewCompanyInput input) {
        fillCreateCompanyForm(input, 0);
        companyCreatePage().getInviteButton().click();
    }
}
