package com.lagou.rabbit.demo_22_springboot_consumerack;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo22SpringbootConsumerAckApplication02 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Demo22SpringbootConsumerAckApplication02.class, args);
    }


    @Bean
    public ApplicationRunner runner() {
        return args -> {
            Thread.sleep(5000);
            for (int i = 0; i < 10; i++) {
                MessageProperties props = new MessageProperties();
                props.setDeliveryTag(i);
                Message message = new Message(("消息：" +
                        i).getBytes("utf-8"), props);
//				this.rabbitTemplate.convertAndSend("ex.biz",
//						"biz", "消息：" + i);
                this.rabbitTemplate.convertAndSend("ex.biz", "biz",
                        message);
            }
        };
    }

}
