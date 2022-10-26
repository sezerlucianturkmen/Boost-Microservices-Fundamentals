package com.boost.utility;

import com.boost.manager.IUserProfileManager;
import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserComponent {

    private final IUserProfileManager userProfileManager;
    private final UserProfileService userProfileService;


    @PostConstruct
    public void firstRun(){
        List<UserProfile> userProfiles=userProfileManager.userList().getBody();
        userProfileService.saveAll(userProfiles);
    }
}
