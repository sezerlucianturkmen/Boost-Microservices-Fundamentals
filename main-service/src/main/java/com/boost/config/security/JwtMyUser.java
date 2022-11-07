package com.boost.config.security;

import com.boost.dto.request.GetAllRolesRequestDto;
import com.boost.manager.IAuthServiceManager;
import com.boost.repository.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtMyUser implements UserDetailsService {

    @Autowired
    IAuthServiceManager authServiceManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadByAuthid(UserProfile profile){
        List<String> roles = authServiceManager.getAllRolesByAuthid(
                GetAllRolesRequestDto.builder()
                        .authid(profile.getAuthid())
                        .build()
        ).getBody();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(role->{
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        });
        return User.builder()
                .username(profile.getUsername())
                .password("")
                .accountExpired(false)
                .accountLocked(false)
                .authorities(grantedAuthorities)
                .build();
    }
}