package com.boost.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(indexName = "userprofile")
public class UserProfile {
    @Id
    Long id;
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String address;
    String avatar;

}