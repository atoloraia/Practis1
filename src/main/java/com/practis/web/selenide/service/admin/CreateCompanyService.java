package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static java.lang.Thread.sleep;

import com.practis.dto.NewCompanyInput;
import lombok.SneakyThrows;

public class CreateCompanyService {

    /** Fill create Company form. */
    public void fillCreateCompanyForm(final NewCompanyInput input) {
        companyCreatePage().getCompanyNameField1().sendKeys(input.getName());
        companyCreatePage().getWorkspaceUrlField().sendKeys(input.getSubdomain());
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

    /** Fill Company form and click 'Invite' button. */
    public void createCompany(final NewCompanyInput input) {
        fillCreateCompanyForm(input);
        companyCreatePage().getCreateButton().click();
    }

    public void fillCompanyName(String text) {
        companyCreatePage().getCompanyNameField1().append(text);
    }

    public void fillWorkspaceUrl(String text) {
        companyCreatePage().getWorkspaceUrlField().append(text);
    }

    public void clickOnConfigureCompany() {
        companyCreatePage().getConfigureCompanyButton().click();
    }

    public void clickOnOpenWebPortalButton() {
        companyCreatePage().getOpenWebPortalButton().click();
    }

    public void clickOnCreateCompany() {
        companyCreatePage().getCreateButton().click();
    }

    public void clickOnCloseButton() {
        companyCreatePage().getCloseButton().click();
    }
}
