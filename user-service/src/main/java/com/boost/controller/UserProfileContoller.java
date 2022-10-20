package com.boost.controller;

import com.boost.dto.request.UserProfileSaveRequestDto;
import com.boost.dto.request.UserProfileUpdateRequestDto;
import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.boost.constants.ApiUrls.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileContoller {

    private final UserProfileService userProfileService;

    /**
     * kullanici kaydi aut service te yapılıyor burada olan bilgiler user service gönderilir
     * auth servide ten gelen olan parametreler
     * 1-username
     * 2-email
     * 3-authid
     * @return
     */

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto){
        return ResponseEntity.ok(userProfileService.save(dto));
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> update(UserProfileUpdateRequestDto dto){

        return ResponseEntity.ok(userProfileService.update(dto));
    }
    @PostMapping(FIND_BY_ID)
    public ResponseEntity<UserProfile> findById(){
        return null;
    }
    @PostMapping(USER_LIST)
    public ResponseEntity<List<UserProfile>> userList(){
        return null;
    }
}