package com.exchange.util.mq;
import com.exchange.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String queue,String message) {
        User user = new User();
        user.setUuid(message);

        user.setId(112);
        rabbitTemplate.convertAndSend(queue, user);
    }
}
