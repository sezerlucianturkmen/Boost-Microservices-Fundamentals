package com.boost.service;
import com.boost.dto.request.UserProfileRequestDto;
import com.boost.graphql.model.UserProfileInput;
import com.boost.repository.IUserProfileRepository;
import com.boost.repository.entity.UserProfile;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void save(UserProfileRequestDto dto){
        save(UserProfile.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .avatar(dto.getAvatar())
                .authid(dto.getAuthid())
                .userid(dto.getId())
                .email(dto.getEmail())
                .build());
    }

    public Boolean saveInput(UserProfileInput userProfileInput){
        save(UserProfile.builder()
                .authid(userProfileInput.getAuthid())
                .username(userProfileInput.getUsername())
                .email(userProfileInput.getEmail())
                .build());
        /**
         * Eğer bu şekilde kayıt işleyecekseniz. o zaman gerçek dataların tutulduğu
         * işlemlerinin yapıldığı microservice lere ilgili kaydı geçmek zorundasınız.
         * bu kısımda, manager kullanarak kayıt işlemini başlatabilirsiniz.
         */
        return true;
    }
}