package com.boost.repository;

import com.boost.repository.entity.Online;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOnlineRepository extends MongoRepository<Online,String> {

    Optional<Online> findOptionalByUserprofileid(String userprofileid);
    List<Online> findAllByOnline(Boolean online);
    List<Online> findByOnlineTrue();
    List<Online> findByOnlineFalse();
}