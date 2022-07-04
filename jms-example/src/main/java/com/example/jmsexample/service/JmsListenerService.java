package com.example.jmsexample.service;

import com.example.jmsexample.config.JmsConfig;
import com.example.jmsexample.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author Artem Kropotov
 * created at 08.06.2022
 **/

@Component
@RequiredArgsConstructor
@Slf4j
public class JmsListenerService {

    private final JmsTemplate jmsTemplate;
    private final MailService mailService;

//    @JmsListener(destination = JmsConfig.GB_QUEUE)
//    public void listen(@Payload HelloWorldMessage helloWorldMessage,
//                       @Headers MessageHeaders messageHeaders) {
//        log.info("Message {} have been gotten", helloWorldMessage);
//        throw new RuntimeException();
//    }

    @JmsListener(destination = JmsConfig.GB_QUEUE_RECEIVE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders messageHeaders, Message message) {
        HelloWorldMessage responseMessage = HelloWorldMessage.builder()
                .message("Response from listener")
                .build();
        log.info("Message {} have been gotten", helloWorldMessage);
//        mailService.sendSimpleMessage(
//                "kropotov.geekbrains@yandex.ru",
//                "kropotov.geek@gmail.com",
//                "Received message",
//                helloWorldMessage.toString());
        try {
            jmsTemplate.convertAndSend(message.getJMSReplyTo(), responseMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
