package com.practis.selenide.admin.create;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInputs;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyData;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyGridRow;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertElementsOnCreateCompanyPage;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.practis.dto.NewCompanyInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
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
        inputData.setEmail(format(inputData.getEmail(), timestamp()));

        companiesToRemove = new ArrayList<>();
        companiesToRemove.add(inputData.getName());
    }

    @TestRailTest(caseId = 5243)
    @DisplayName("Check WEB Elements on 'New Company Account' page")
    void checkElementsNewCompany() {
        assertElementsOnCreateCompanyPage();
    }

    @TestRailTest(caseId = 45)
    @DisplayName("Create Company")
    void createCompany() {
        companyService().createCompany(inputData);
        companiesToRemove.add(inputData.getName());

        // assert message
        snackbar().getMessage().shouldBe(exactText("1 Company has been created"));

        // assert company in company selector list
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

        // Check snackbar message “User with this email [email] already exists!“
        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                format(
                                        "User with email %s already exists!",
                                        inputData.getEmail())));

        // assert grid row data
        open(webApplicationConfig().getAdminUrl());
        final var companyGridRow =
                companyAccoutsService().searchCompany(inputData.getName()).getRowElement();
        assertTrue(companyGridRow.exists());
    }

    @TestRailTest(caseId = 47)
    @DisplayName("Create Admin: CRUD for multiple adding.")
    void crudNewCompany() {
        // fill data for Company_1
        final var inputs =
                getNewCompanyInputs().stream()
                        .limit(3)
                        .peek(
                                inputData ->
                                        inputData.setName(format(inputData.getName(), timestamp())))
                        .peek(
                                inputData ->
                                        inputData.setEmail(
                                                format(inputData.getEmail(), timestamp())))
                        .collect(toList());

        // ==============
        final var firstCompany = inputs.get(0);
        companyService().fillCreateCompanyForm(firstCompany, 0);
        companyService().deleteRow(0);

        // assert invite button disabled
        companyCreatePage().getInviteButton().shouldBe(disabled);

        // ==============
        final var secondCompany = inputs.get(1);

        companyService().addRow();
        companyService().fillCreateCompanyForm(secondCompany, 0);

        // ==============
        final var thirdCompany = inputs.get(2);

        companyService().addRow();
        companyService().fillCreateCompanyForm(thirdCompany, 1);

        companiesToRemove.add(secondCompany.getName());
        companiesToRemove.add(thirdCompany.getName());
        companyService().clickInvite();

        // assert message
        snackbar().getMessage().shouldBe(exactText("2 Companies have been created"));
        awaitElementNotExists(10, () -> snackbar().getMessage());

        // assert edit page data
        Stream.of(secondCompany, thirdCompany)
                .forEach(
                        company -> {
                            final var companyGridRow =
                                    companyAccoutsService()
                                            .searchCompany(company.getName())
                                            .getRowElement();
                            companyGridRow.click();
                            assertCompanyData(company, companySettingsPage());
                        });
    }

    @AfterEach
    void cleanup() {
        companiesToRemove.forEach(name -> practisApi().deactivateCompany(name));
    }
}
