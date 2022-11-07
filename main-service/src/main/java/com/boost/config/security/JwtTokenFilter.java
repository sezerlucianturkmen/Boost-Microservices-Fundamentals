package com.boost.config.security;

import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import com.boost.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    JwtMyUser jwtMyUser;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader!= null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            Optional<Long> authid = jwtTokenManager.getByIdFromToken(token);
            if(authid.isPresent()){
                Optional<UserProfile> userProfile = userProfileService.findByAuthid(authid.get());
                if(userProfile.isPresent()){
                    UserDetails userDetails = jwtMyUser.loadByAuthid(userProfile.get());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,null,
                                    userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}