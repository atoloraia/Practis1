package com.practis.selenide.admin.create;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyData;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyGridRow;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertElementsOnCreateCompanyPage;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.Selenide;
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
class NewCompanyTest {

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

    @TestRailTest(caseId = 5243)
    @DisplayName("Company: Create: Check Elements")
    void checkElementsNewCompany() {
        assertElementsOnCreateCompanyPage();
    }

    @TestRailTest(caseId = 45)
    @DisplayName("Company: Create")
    void createCompany() {
        companyService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // assert message
        snackbar().getMessage().shouldBe(exactText("1 Company has been created"));

        // assert company in company selector list
        Selenide.refresh();
        companySelector().open();
        final var companyInSelector = companySelector().findCompany(inputData.getName());
        assertTrue(companyInSelector.exists());

        // assert grid row data
        final var companyGridRow = companyAccoutsService().searchCompany(inputData.getName());
        assertCompanyGridRow(inputData, companyGridRow);

        // assert edit page data
        companyGridRow.click();
        assertCompanyData(inputData, companySettingsPage());
    }

    @TestRailTest(caseId = 46)
    @DisplayName("Create Company: Validation: Already used email")
    void validation_UserExists() {
        practisApi().createCompany(inputData);
        companiesToRemove.add(inputData.getName());
        companyService().createCompany(inputData);

        // assert grid row data
        open(webApplicationConfig().getAdminUrl());
        final var companyGridRow =
                companyAccoutsService().searchCompany(inputData.getName()).getRowElement();
        assertTrue(companyGridRow.exists());
    }

    @AfterEach
    void cleanup() {
        companiesToRemove.forEach(name -> practisApi().deactivateCompany(name));
    }
}
