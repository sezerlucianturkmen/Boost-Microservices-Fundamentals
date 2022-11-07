package com.boost.service;

import com.boost.dto.request.GetMyProfileRequestDto;
import com.boost.repository.IOnlineRepository;
import com.boost.repository.entity.Online;
import com.boost.repository.entity.UserProfile;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OnlineService extends ServiceManager<Online,String> {
    private final IOnlineRepository iOnlineRepository;
    private final UserProfileService userProfileService;
    public OnlineService(IOnlineRepository iOnlineRepository, UserProfileService userProfileService) {
        super(iOnlineRepository);
        this.iOnlineRepository = iOnlineRepository;
        this.userProfileService = userProfileService;
    }

    public List<Online> getAllOnlineList(){
        return iOnlineRepository.findByOnlineTrue();
    }

    /**
     * token ile birlikte userProfileServisten ilgili kişiye ait profil bilgisi çekilir.
     * sonra bu bilgiler ileonline olma işlemleri yapılır.
     * @param token
     */
    public void doOnline(String token){
        UserProfile userProfile = userProfileService.findByToken(
                GetMyProfileRequestDto.builder().token(token).build()
        );
        /**
         * Kişinin daha önceden online/offline bilgisinin kayıtlı olup olmadığına bakıyoruz.
         */
        Optional<Online> online = iOnlineRepository.findOptionalByUserprofileid(userProfile.getId());
        if(online.isEmpty()){
            save(Online.builder()
                    .online(true)
                    .userprofileid(userProfile.getId())
                    .build());
        }else{
            online.get().setOnline(true);
            save(online.get());
        }
    }

    public void doOffline(String token){
        UserProfile userProfile = userProfileService.findByToken(
                GetMyProfileRequestDto.builder().token(token).build()
        );
        /**
         * Kişinin daha önceden online/offline bilgisinin kayıtlı olup olmadığına bakıyoruz.
         */
        Optional<Online> online = iOnlineRepository.findOptionalByUserprofileid(userProfile.getId());
        if(online.isEmpty()){
            save(Online.builder()
                    .online(false)
                    .userprofileid(userProfile.getId())
                    .build());
        }else{
            online.get().setOnline(false);
            save(online.get());
        }
    }
}