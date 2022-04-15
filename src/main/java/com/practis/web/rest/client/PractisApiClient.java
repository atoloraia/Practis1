package com.practis.web.rest.client;

import com.practis.web.rest.dto.RestAdminRequest;
import com.practis.web.rest.dto.RestAdminResponse;
import com.practis.web.rest.dto.RestLoginRequest;
import com.practis.web.rest.dto.RestLoginResponse;
import com.practis.web.rest.dto.RestSearchRequest;
import com.practis.web.rest.dto.RestSearchResponse;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface PractisApiClient {

  @RequestLine("POST /api/auth/login")
  RestLoginResponse login(RestLoginRequest request);

  @RequestLine("POST /api/admin/users")
  List<RestAdminResponse> createAdmin(List<RestAdminRequest> request);

  @RequestLine("DELETE /api/admin/users/{adminId}")
  void deleteAdmin(@Param("adminId") Integer adminId);

  @RequestLine("POST /api/admin/users/practis_admin/search")
  RestSearchResponse<RestAdminResponse> searchAdmin(RestSearchRequest adminId);
}
