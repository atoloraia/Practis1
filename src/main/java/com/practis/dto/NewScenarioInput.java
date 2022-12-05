package com.practis.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewScenarioInput {

    String title;
    String description;
    String customerLine;
    String repLine;
    List<String> customerLines;
    List<String> repLines;
}
