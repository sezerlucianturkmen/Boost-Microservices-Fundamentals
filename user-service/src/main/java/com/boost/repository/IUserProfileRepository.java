package com.boost.repository;

import com.boost.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {

Optional<UserProfile> findOptionalByAuthid(Long authid);

}
