package com.practis.support.extension.dto;

import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestTeamResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamWithChildren {

  RestTeamResponse team;
  List<NewUserInput> users;
  List<NewPractisSetInput> practisSets;

}
