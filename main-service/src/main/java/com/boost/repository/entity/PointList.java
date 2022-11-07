package com.boost.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class PointList {
    @Id
    String id;
    String userId;
    String competitionId;
    Integer point;
}