package com.practis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewCompanyInput {

    String name;
    String subdomain;
}
