package com.boost.controller;

import com.boost.dto.request.*;
import com.boost.dto.response.FindByAuthidResponseDto;
import com.boost.repository.entity.UserProfile;
import com.boost.service.OnlineService;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.boost.constants.ApiUrls.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileContoller {

    private final UserProfileService userProfileService;
    private final OnlineService onlineService;
    @PostMapping("/getmyprofile")
    public ResponseEntity<UserProfile> getMyProfile(@RequestBody @Valid GetMyProfileRequestDto dto){
        return ResponseEntity.ok(userProfileService.findByToken(dto));
    }

    @PostMapping("/doonline")
    public ResponseEntity<Void> doOnline(DoOnlineRequestDto dto){
        onlineService.doOnline(dto.getToken());
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/dooffline")
    public ResponseEntity<Void> doOffline(DoOnlineRequestDto dto){
        onlineService.doOffline(dto.getToken());
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/getallonlinelist")
    public ResponseEntity<?> getAllOnlineList(GetAllOnlineListRequestDto dto){
        return ResponseEntity.ok(onlineService.getAllOnlineList());
    }


    @GetMapping("/getupper")
    public ResponseEntity<String> getUpperCase(Long authid) {
        return ResponseEntity.ok(userProfileService.getUpperCase(authid));
    }
    @PostMapping("/saveall")
    public ResponseEntity<Void> saveAll(@RequestBody List<UserProfileSaveRequestDto> dtos){
        dtos.forEach(dto->userProfileService.save(dto));
        return ResponseEntity.ok().build();
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
    @PostMapping("updatenontoken")
    public ResponseEntity<Boolean> updateNonToken(UserProfileUpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateNonToken(dto));
    }
    @PostMapping(FIND_BY_ID)
    public ResponseEntity<UserProfile> findById(){
        return null;
    }
    @GetMapping(USER_LIST)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('PRODUCTOWNER')")
    public ResponseEntity<List<UserProfile>> userList(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
    /**
     * localhost:9092/getallpage?page=0&size=20&parameter=id&direction=ASC
     * localhost:9092/getallpage/0/20/id/ASC
     * */
    @GetMapping("/getallpage")
    public ResponseEntity<Page<UserProfile>> getAllPage(int pageSize,int pageNumber, String parameter, String direction){
        return ResponseEntity.ok(userProfileService.getAllPage(pageSize,pageNumber,parameter,direction));
    }
    @GetMapping("/getallslice")
    public ResponseEntity<Slice<UserProfile>> getAllSlice(int pageSize, int pageNumber, String parameter, String direction){
        return ResponseEntity.ok(userProfileService.getAllSlice(pageSize,pageNumber,parameter,direction));
    }

    @PostMapping("/findbyauthid")
    public ResponseEntity<FindByAuthidResponseDto> findByAuthid(@RequestBody FindByAuthidRequestDto dto){
        UserProfile user = userProfileService.findByAuthid(dto);
        return ResponseEntity.ok(FindByAuthidResponseDto.builder()
                .avatar(user.getAvatar())
                .name(user.getName())
                .userid(user.getId())
                .username(user.getUsername())
                .build());
    }
}