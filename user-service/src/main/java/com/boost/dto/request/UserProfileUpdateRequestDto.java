package com.boost.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfileUpdateRequestDto {
    Long authid;
    String token;
    String name;
    String surname;
    String phone;
    String address;
    String avatar;

}