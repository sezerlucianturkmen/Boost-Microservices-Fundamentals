package com.boost.graphql.query;

import com.boost.repository.IUserProfileRepository;
import com.boost.repository.entity.UserProfile;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileQueryResolver implements GraphQLQueryResolver {

    private final IUserProfileRepository userProfileRepository;

    public Iterable<UserProfile> findByUsername(String username){
        return userProfileRepository.findByUsernameContaining(username);
    }

    public Iterable<UserProfile> findAll(){
        return userProfileRepository.findAll();
    }
}
