package com.practis.selenide.company;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teams;
import static com.practis.web.selenide.configuration.data.company.NewTeamInputData.getNewTeamInput;

import com.practis.dto.NewTeamInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.web.util.SelenidePageLoadAwait;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
class CreateTeamTest {

  private NewTeamInput inputData;
  private List<String> teamsToRemove;

  @BeforeEach
  void init() {
    newItemSelector().create("Team");

    inputData = getNewTeamInput();
    inputData.setName(String.format(inputData.getName(), timestamp()));

    teamsToRemove = new ArrayList<>();
    teamsToRemove.add(inputData.getName());
  }

  @Test
  void createTeam() {
    teams().createTeam(inputData);

    System.out.println(1);
  }

  @AfterEach
  void cleanup() {
    //teamsToRemove.forEach(label -> practisApi().deleteLabel(label));
  }
}
