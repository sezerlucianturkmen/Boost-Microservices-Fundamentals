package com.boost.manager;

import com.boost.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.boost.constants.ApiUrls.*;

@FeignClient(name="user-service",
        url="${myapplication.user-service.feign-client}/user",
        decode404 = true)
public interface IUserProfileManager {

    @GetMapping(USER_LIST)
    public ResponseEntity<List<UserProfile>> userList();
}
