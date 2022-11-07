package com.boost.repository;

import com.boost.repository.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Optional<Authorities> findByName(String name);

}