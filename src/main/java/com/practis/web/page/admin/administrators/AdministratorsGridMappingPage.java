package com.practis.web.page.admin.administrators;

import static com.practis.utils.AwaitUtils.awaitSeconds;
import static com.practis.web.page.admin.administrators.mapper.AdministratorsMapper.fromGridRow;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.dto.admin.Administrator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.retry.annotation.Retryable;

@PractisPage
@Retryable
public class AdministratorsGridMappingPage extends AdministratorsGridActionPage {

  public Administrator getFirstAdministratorInGrid() {
    return getAdministrators().get(0);
  }

  public List<Administrator> getAdministrators() {
    return getAdministrators(0);
  }

  /**
   * To be added.
   */
  public List<Administrator> getAdministrators(final int expectedRowsNumber) {
    if (expectedRowsNumber > 0) {
      awaitSeconds(3, () -> administratorsGridRowElements.size() == expectedRowsNumber);
    }
    return administratorsGridRowElements.stream()
        .map(row -> fromGridRow(row, getGridRowColumnSelector()))
        .collect(Collectors.toList());
  }
}
