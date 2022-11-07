package com.boost.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FindByAuthidResponseDto {
    String userid;
    String username;
    String name;
    String avatar;
}