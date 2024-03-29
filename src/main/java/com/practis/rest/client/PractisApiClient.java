package com.practis.rest.client;

import com.practis.rest.dto.RestCollection;
import com.practis.rest.dto.RestSearchRequest;
import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.rest.dto.company.RestCreateDraftUserRequest;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestDeleteDraftUserRequest;
import com.practis.rest.dto.company.RestSearchLabelResponse;
import com.practis.rest.dto.company.RestStagingResponse;
import com.practis.rest.dto.company.audio.SaveFileResponse;
import com.practis.rest.dto.company.library.RestCreateLabelRequest;
import com.practis.rest.dto.user.SetCompanyRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.io.File;

public interface PractisApiClient {

    // v1
    @RequestLine("DELETE /api/admin/users/{userId}")
    @Headers("Content-Type: application/json")
    void deleteUser(@Param("userId") Integer userId);

    // v1
    @RequestLine("DELETE /api/staging/")
    @Headers("Content-Type: application/json")
    void deleteDraftUser(RestDeleteDraftUserRequest request);

    // v1
    @RequestLine("POST /api/staging/")
    @Headers("Content-Type: application/json")
    RestStagingResponse createDraftUser(RestCreateDraftUserRequest request);

    // v1
    @RequestLine("POST /api/staging/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestStagingResponse> searchDraftUser(RestSearchRequest stagingId);

    // v1
    @RequestLine("POST /api/labels/search")
    @Headers("Content-Type: application/json")
    RestCollection<RestSearchLabelResponse> searchLabel(RestSearchRequest searchRequest);

    // v1
    @RequestLine("POST /api/labels/")
    @Headers("Content-Type: application/json")
    RestCreateLabelResponse createLabel(RestCreateLabelRequest createRequest);

    // v1
    @RequestLine("DELETE /api/labels/{labelId}")
    @Headers("Content-Type: application/json")
    void deleteLabel(@Param("labelId") Integer labelId);

    // v1
    @RequestLine("PUT /api/admin/users/{userId}?skipLog=true")
    @Headers("Content-Type: application/json")
    RestAdminResponse updateUser(@Param("userId") Integer userId, SetCompanyRequest request);

    // v1
    @RequestLine("POST /api/files")
    @Headers("Content-Type: multipart/form-data")
    SaveFileResponse uploadLine(
            @Param("file") File file,
            @Param("type") String type,
            @Param("associatedEntityType") String associatedEntityType);
}
