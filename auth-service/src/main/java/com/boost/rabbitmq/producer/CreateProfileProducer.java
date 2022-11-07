package com.boost.rabbitmq.producer;

import com.boost.rabbitmq.model.CreateProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProfileProducer {
    private final RabbitTemplate rabbitTemplate;

    public void createProfile(CreateProfile model){
        rabbitTemplate.convertAndSend("auth-direct-exchange", "bind-key-create-user", model);
    }
}