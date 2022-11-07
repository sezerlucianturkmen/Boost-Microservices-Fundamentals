package com.boost.service;

import com.boost.repository.IAuthRolesRepository;
import com.boost.repository.entity.AuthRoles;
import com.boost.repository.entity.Authorities;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthRolesServices extends ServiceManager<AuthRoles,Long> {
    private final IAuthRolesRepository iAuthRolesRepository;
    private final AuthoritiesService   authoritiesServices;
    public AuthRolesServices(IAuthRolesRepository iAuthRolesRepository,
                             AuthoritiesService   authoritiesServices) {
        super(iAuthRolesRepository);
        this.iAuthRolesRepository = iAuthRolesRepository;
        this.authoritiesServices  = authoritiesServices;
    }

    public List<String> getRolesByAuthid(Long authid){
        /**
         * öncelikle authid ye ait tüm rol listesini çekiyoruz.
         */
        List<AuthRoles> authRoles = iAuthRolesRepository.findAllByAuthid(authid);
        /**
         * çekilen listedeki roles id leri ile Authorities tabosundan ilgili rolleri çekiyoruz.
         */
        List<String> roles = new ArrayList<>();
        authRoles.forEach(r->{
            Authorities authorities = authoritiesServices.findById(r.getRoleid());
            if(authorities!=null)
                roles.add(authorities.getName());
        });
        return roles;
    }
}