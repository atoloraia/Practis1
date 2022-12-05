package com.practis.support.extension.dto;

import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamWithChildren {

    NewTeamInput team;
    List<NewUserInput> users;
    List<NewPractisSetInput> practisSets;
}
