package com.boost.controller;

import com.boost.dto.request.UserProfileSaveRequestDto;
import com.boost.dto.request.UserProfileUpdateRequestDto;
import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.boost.constants.ApiUrls.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileContoller {

    private final UserProfileService userProfileService;

    @GetMapping("/getupper")
    public ResponseEntity<String> getUpperCase(Long authid) {
        return ResponseEntity.ok(userProfileService.getUpperCase(authid));
    }

    @PostMapping("/savecachable")
    public ResponseEntity<Void> updateUser(@RequestBody UserProfile userProfile){
        userProfileService.updateCacheReset(userProfile);
        return ResponseEntity.ok().build();
    }


    /**
     * Kullanıcı kaydı, auth service te yapılıyor ve burada olan bilgiler user-service e gönderiliyor.
     * Auth-Service ten gelecek olan parametreler:
     * 1- username
     * 2- email
     * 3- authid
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
    @GetMapping(USER_LIST)
    public ResponseEntity<List<UserProfile>> userList(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
    @GetMapping("/gellallpage")
    public ResponseEntity<Page<UserProfile>> getAllPage(int page,int size, String parameter,String direction){
        return ResponseEntity.ok(userProfileService.getAllPage(page, size, parameter, direction));
    }
    @GetMapping("/gellallslice")
    public ResponseEntity<Page<UserProfile>> getAllslice(int page,int size, String parameter,String direction){
        return ResponseEntity.ok(userProfileService.getAllSlice(page, size, parameter, direction));
    }
}