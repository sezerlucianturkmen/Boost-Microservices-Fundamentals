package com.boost.repository;

import com.boost.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile, String> {

Optional<UserProfile> findOptionalByAuthid(Long authid);

}
