package com.boost.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tblauthroles")
@Entity
public class AuthRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long authid;
    Long roleid;
    Long adddate;
    Long adduser;
    Boolean state;
}