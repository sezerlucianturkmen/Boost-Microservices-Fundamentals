package com.boost.service;

import com.boost.repository.IAuthoritiesRepository;
import com.boost.repository.entity.Authorities;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService extends ServiceManager<Authorities,Long> {

    private final IAuthoritiesRepository authoritiesRepository;

    public AuthoritiesService(IAuthoritiesRepository authoritiesRepository) {
        super(authoritiesRepository);
        this.authoritiesRepository = authoritiesRepository;
    }
}