package com.boost.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Question implements Serializable {
    @Id
    String id;
    String question;
    Integer point;
    Long answerTime;
}