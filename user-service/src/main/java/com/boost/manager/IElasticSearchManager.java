package com.boost.manager;

import com.boost.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.boost.constants.ApiUrls.*;


@FeignClient(name="elastic-service",
        url="${myapplication.elastic-service.feign-client}",
        decode404 = true)
public interface IElasticSearchManager {

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody UserProfile userProfile);
    @PostMapping(UPDATE)
    public ResponseEntity<Void> update(@RequestBody UserProfile userProfile);




}
