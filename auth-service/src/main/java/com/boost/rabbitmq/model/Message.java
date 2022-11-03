package com.boost.rabbitmq.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
/**
 * RabbitMQ ile pojo, iletilim yaparken, java sınıflarının serileştirilmesi gereklidir.
 * çünkü bu bilgi json olarak iletilir ve çözülür.
 */
public class Message implements Serializable {
    String messsage;
    Long code;
}