package com.boost.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Builder
public class UserProfileRequestDto {
    Long id;
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String address;
    String avatar;

    @JsonCreator
    public UserProfileRequestDto(Long id, Long authid, String username, String name, String surname, String email, String phone, String address, String avatar) {
        this.id = id;
        this.authid = authid;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
    }
}