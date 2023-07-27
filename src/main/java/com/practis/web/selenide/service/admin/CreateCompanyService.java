package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;

import com.practis.dto.NewCompanyInput;

public class CreateCompanyService {

    /** Fill create Company form. */
    public void fillCreateCompanyForm(final NewCompanyInput input) {
        companyCreatePage().getCompanyNameField1().sendKeys(input.getName());
        companyCreatePage().getWorkspaceUrlField().sendKeys(input.getSubdomain());
    }

    /** Fill Company form and click 'Invite' button. */
    public void createCompany(final NewCompanyInput input) {
        fillCreateCompanyForm(input);
        companyCreatePage().getCreateButton().click();
    }

    public void closeCompanyCreateModal() {
        companyCreatePage().getCrossButton().click();
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
