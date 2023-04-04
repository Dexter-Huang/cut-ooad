package com.exchange.util.mq;

import com.exchange.entity.User;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @RabbitListener(queuesToDeclare = @Queue("boot_queue"))
    public void consumer(User user){
        System.out.println("消息内容为：" + user.getUuid());
        System.out.println("消息内容为：" + user.getId());
    }
}
