package com.boost.controller;

import com.boost.dto.request.UserProfileSaveRequestDto;
import com.boost.dto.request.UserProfileUpdateRequestDto;
import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
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
    public ResponseEntity<String> getUpperCase(Long id) {
        return ResponseEntity.ok(userProfileService.getUpperCase(id));
    }
    @PostMapping("/savecachable")
    public ResponseEntity<Void> updateUser(@RequestBody UserProfile userProfile){
        userProfileService.updateCacheReset(userProfile);
        return ResponseEntity.ok().build();
    }
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
        return null;
    }
}