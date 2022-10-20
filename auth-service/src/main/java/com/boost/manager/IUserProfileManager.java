package com.boost.manager;

import com.boost.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.boost.constants.ApiUrls.*;
/**
 * Not: Bu isim benzersiz bir isim olmalıdır.
 * Diğer türlü hata alınır.
 */
@FeignClient(name = "user-profile-service",
        url= "http://localhost:9092/api/v1/user",
        decode404 = true)
public interface IUserProfileManager {

    @PostMapping(SAVE)
    ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto);

}
