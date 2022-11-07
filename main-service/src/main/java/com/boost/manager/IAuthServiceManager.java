package com.boost.manager;
import com.boost.dto.request.GetAllRolesRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "auth-service-manager",
        url="${myapplication.auth-service.url}/auth",
        decode404 = true)
public interface IAuthServiceManager {

    @PostMapping("/getrolesbyauthid")
    ResponseEntity<List<String>> getAllRolesByAuthid(@RequestBody GetAllRolesRequestDto dto);
}