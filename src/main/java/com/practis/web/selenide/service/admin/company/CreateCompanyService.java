package com.practis.web.selenide.service.admin.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;

import com.practis.dto.NewCompanyInput;

public class CreateCompanyService {

    // New Company form
    /** Fill "New Company" form */
    public void fillCreateCompanyForm(final NewCompanyInput input) {
        fillCompanyName(input.getName());
        fillWorkspaceUrl(input.getSubdomain());
    }

    /** Fill "Company Name" on "New Company" form */
    public void fillCompanyName(String text) {
        companyCreatePage().getCompanyNameField1().append(text);
    }

    /** Fill "Workspace URL" on "New Company" form */
    public void fillWorkspaceUrl(String text) {
        companyCreatePage().getWorkspaceUrlField().append(text);
    }

    /** Click "Create" button */
    public void clickOnCreateCompany() {
        companyCreatePage().getCreateButton().click();
    }

    /** Fill "New Company" form and click 'Create' button. */
    public void createCompany(final NewCompanyInput input) {
        fillCreateCompanyForm(input);
        clickOnCreateCompany();
    }

    /** Close "New Company" form */
    public void closeCompanyCreateModal() {
        companyCreatePage().getCloseButton().click();
    }

    // Company modal
    /** Click on "Configure Company" button */
    public void clickOnConfigureCompany() {
        companyCreatePage().getConfigureCompanyButton().click();
    }

    /** Click on "Open Web Portal" button */
    public void clickOnOpenWebPortalButton() {
        companyCreatePage().getOpenWebPortalButton().click();
    }

    // Company modal
}
