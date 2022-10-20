package com.boost.service;

import com.boost.dto.request.LoginRequestDto;
import com.boost.dto.request.RegisterRequestDto;
import com.boost.dto.request.UserProfileSaveRequestDto;
import com.boost.exception.AuthServiceException;
import com.boost.exception.ErrorType;
import com.boost.manager.IUserProfileManager;
import com.boost.repository.IAuthRepository;
import com.boost.repository.entity.Auth;
import com.boost.repository.enums.Roles;
import com.boost.utility.ServiceManager;
import com.boost.utility.TokenManager;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final TokenManager tokenManager;
    private final IUserProfileManager userProfileManager;
    public AuthService(IAuthRepository repository,
                       IUserProfileManager userProfileManager,
                       TokenManager tokenManager) {
        super(repository);
        this.repository = repository;
        this.userProfileManager = userProfileManager;
        this.tokenManager = tokenManager;
    }

    public Boolean save(RegisterRequestDto dto){
        Auth auth = Auth.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(Roles.ROLE_USER)
                .build();
        if(dto.getAdmincode()!=null)
            if(dto.getAdmincode().equals("Adm!n"))
                auth.setRoles(Roles.ROLE_ADMIN);
        save(auth);
        if(auth.getId() != null){
            userProfileManager.save(UserProfileSaveRequestDto.builder()
                    .authid(auth.getId())
                    .email(auth.getEmail())
                    .username(auth.getUsername())
                    .build());
            return true;
        }
        return false;
    }

    public String doLogin(LoginRequestDto dto){
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(
                dto.getUsername(),dto.getPassword());
        if(auth.isEmpty()) throw new AuthServiceException(ErrorType.LOGIN_ERROR_001);
        return tokenManager.generateToken(auth.get().getId());
    }

}