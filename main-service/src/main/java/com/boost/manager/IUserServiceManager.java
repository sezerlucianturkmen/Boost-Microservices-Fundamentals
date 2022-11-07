package com.boost.manager;

import com.boost.dto.request.FindByAuthidRequestDto;
import com.boost.dto.response.FindByAuthidResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service",url = "${myapplication.user-service.feign-client}/user",decode404 = true)
public interface IUserServiceManager {
    @PostMapping("/findbyauthid")
    ResponseEntity<FindByAuthidResponseDto> findByAuthid(@RequestBody FindByAuthidRequestDto dto);
}