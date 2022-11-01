package com.boost.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



//@Table(name = "tbluserprofile")
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "userprofile")
public class UserProfile {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    String id;
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String address;
    String avatar;

}