package com.boost.repository;


import com.boost.repository.entity.AuthRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthRolesRepository extends JpaRepository<AuthRoles,Long> {
    List<AuthRoles> findAllByAuthid(Long authid);
}