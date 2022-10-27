package com.boost.graphql.mutation;

import com.boost.graphql.model.UserProfileInput;
import com.boost.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {

    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput userProfileInput){
        userProfileService.saveInput(userProfileInput);
        return true;
    }


}
