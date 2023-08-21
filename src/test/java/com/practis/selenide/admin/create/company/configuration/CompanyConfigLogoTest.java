package com.practis.selenide.admin.create.company.configuration;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyConfigurationService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyCreateService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.editPhotoPopUpService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertLogoTabDefault;
import static com.practis.web.selenide.validator.admin.CompanyConfigurationValidator.assertLogoTabWithLogo;
import static com.practis.web.selenide.validator.popup.EditPopUpValidator.assertEditPhotoPopUp;
import static java.lang.String.format;

import com.practis.dto.NewCompanyInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
public class CompanyConfigLogoTest {

    private List<String> companiesToRemove;
    private NewCompanyInput inputData;

    @BeforeEach
    void beforeEach() {
        newItemSelector().create("New Company");

        inputData = getNewCompanyInput();
        inputData.setName(format(inputData.getName(), timestamp()));
        inputData.setSubdomain(format(inputData.getSubdomain(), timestamp()));

        companiesToRemove = new ArrayList<>();
        companiesToRemove.add(inputData.getName());
    }

    @TestRailTest(caseId = 32205)
    @DisplayName("Configure Company: Logo: Check Elements")
    void checkElementsCompanyConfigLogo() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // click on "Configure Company"
        companyCreateService().clickOnConfigureCompany();

        // assert elements on Logo tab
        assertLogoTabDefault();
    }

    @TestRailTest(caseId = 32209)
    @DisplayName("Configure Company: Logo: Upload")
    void companyConfigLogoUpload() {
        companyCreateService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // click on "Configure Company"
        companyCreateService().clickOnConfigureCompany();

        // upload company Logo
        companyConfigurationService().uploadCompanyLogo();

        // assert "Edit Photo" pop up and click "Save"
        assertEditPhotoPopUp();
        editPhotoPopUpService().clickSaveButton();

        // click on Camera icon
        assertLogoTabWithLogo();
    }

    @AfterEach
    void cleanup() {
        companiesToRemove.forEach(name -> practisApi().deactivateCompany(name));
    }
}
