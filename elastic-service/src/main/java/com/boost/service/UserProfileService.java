package com.boost.service;

import com.boost.repository.IUserProfileRepository;
import com.boost.repository.entity.UserProfile;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository userProfileRepository;

    public UserProfileService(IUserProfileRepository userProfileRepository){
        super(userProfileRepository);
        this.userProfileRepository=userProfileRepository;
    }


}
