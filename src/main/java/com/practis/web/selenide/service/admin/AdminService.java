package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static java.lang.Thread.sleep;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;

import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewAdminInput;
import com.practis.web.selenide.component.GridRow;
import lombok.SneakyThrows;

public class AdminService {

    /** Get password validation message. */
    public SelenideElement getPasswordValidationMessage() {
        return adminCreatePage().getPasswordValidationError().get(0);
    }

    /** Fill create Admin form. */
    public void fillCreateAdminForm(final NewAdminInput input, final int rowNum) {
        adminCreatePage().getEmailFieldElements().get(rowNum).sendKeys(input.getEmail());
        adminCreatePage().getFirstNameField().get(rowNum).sendKeys(input.getFirstName());
        adminCreatePage().getLastNameField().get(rowNum).sendKeys(input.getLastName());
        adminCreatePage().getPasswordField().get(rowNum).sendKeys(input.getPassword());
    }

    /** Delete new Admin row. */
    public void deleteRow(final int rowNum) {
        adminCreatePage().getDeleteRowButton().get(rowNum).click();
    }

    /** Add new Admin row. */
    public void addRow() {
        adminCreatePage().getAddRowLink().click();
    }

    /** Click 'Show Password' button. */
    public void showPassword(final int rowNum) {
        adminCreatePage().getShowPassword().get(rowNum).click();
    }

    /** Click 'Hide Password' button. */
    public void hidePassword(final int rowNum) {
        adminCreatePage().getHidePassword().get(rowNum).click();
    }

    /** Click 'Create' button. */
    @SneakyThrows
    public void clickCreate() {
        sleep(2000);
        adminCreatePage().getCreateButton().click();
    }

    /** Search admin on grid by email. */
    public GridRow searchAdmin(final String email) {
        awaitSoft(10, () -> grid().getTableRows().size() > 0);
        navigationAdmin().adminNavigationItem.click();
        search().search(email);

        return awaitGridRowExists(15, () -> grid().getRow(email));
    }

    /** Create Admin. */
    public void createAdmin(final NewAdminInput input) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        fillCreateAdminForm(input, 0);
        adminCreatePage().getCreateButton().click();
    }
}
