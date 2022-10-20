package com.boost.service;

import com.boost.dto.request.UserProfileSaveRequestDto;
import com.boost.dto.request.UserProfileUpdateRequestDto;
import com.boost.exception.ErrorType;
import com.boost.exception.UserServiceException;
import com.boost.repository.IUserProfileRepository;
import com.boost.repository.entity.UserProfile;
import com.boost.utility.JwtTokenManager;
import com.boost.utility.ServiceManager;
import com.boost.utility.TokenManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {


    private final IUserProfileRepository iUserProfileRepository;
    private final JwtTokenManager tokenManager;
    public UserProfileService(IUserProfileRepository iUserProfileRepository,
                              JwtTokenManager tokenManager) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
        this.tokenManager = tokenManager;
    }

    public Boolean save(UserProfileSaveRequestDto dto){
        save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
        return true;
    }

    public Boolean update(UserProfileUpdateRequestDto dto){
        Optional<Long> authid = tokenManager.getByIdFromToken(dto.getToken());
        if(authid.isEmpty()) throw new UserServiceException(ErrorType.GECERSIZ_TOKEN);
        Optional<UserProfile> userProfile =
                iUserProfileRepository.findOptionalByAuthid(authid.get());
        if(userProfile.isEmpty()) throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        UserProfile profile = userProfile.get();
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());
        profile.setAvatar(dto.getAvatar());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        save(profile);
        return true;
    }
}