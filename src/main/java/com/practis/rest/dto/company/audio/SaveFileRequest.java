package com.practis.rest.dto.company.audio;

import feign.form.FormData;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaveFileRequest {

    private FormData file;
    @Builder.Default private String type = "AUDIO";
    @Builder.Default private String associatedEntityType = "Line";
}
