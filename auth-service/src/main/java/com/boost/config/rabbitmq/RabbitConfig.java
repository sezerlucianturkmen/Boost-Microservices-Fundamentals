package com.boost.config.rabbitmq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String authDirectExchange = "auth-direct-exchange";
    private String bindingKeyCreateUser = "bind-key-create-user";
    private String queueCreateUser = "queue-auth-create-user";

    @Bean
    DirectExchange authDirectExchange() {
        return new DirectExchange(authDirectExchange);
    }
    @Bean
    Queue queueCreateUser() {
        return new Queue(queueCreateUser);
    }
    @Bean
    public Binding bindingCreateUser(final Queue queueCreateUser, final DirectExchange authDirectExchange ){
        /**
         * Exchange ile Queue arasında binding işlemi yapılır.
         * şu queue ile bu exchange i bu anahtar ile bağla
         */
        return BindingBuilder.bind(queueCreateUser).to(authDirectExchange).with(bindingKeyCreateUser);
    }

}