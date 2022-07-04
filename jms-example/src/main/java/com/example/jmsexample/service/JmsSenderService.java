package com.example.jmsexample.service;

import com.example.jmsexample.config.JmsConfig;
import com.example.jmsexample.model.HelloWorldMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author Artem Kropotov
 * created at 08.06.2022
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class JmsSenderService {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

//    @Scheduled(fixedRate = 10000)
//    public void sendMessage() {
//        HelloWorldMessage message = HelloWorldMessage.builder()
//                .message("Hello World")
//                .build();
//
//        jmsTemplate.convertAndSend(JmsConfig.GB_QUEUE, message);
//    }

    @Scheduled(fixedRate = 5000)
    public void sendAndReceiveMessage() {
        HelloWorldMessage message = HelloWorldMessage.builder()
                .message("Hello World")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.GB_QUEUE_RECEIVE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                try {
                    TextMessage textMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    textMessage.setStringProperty("_type", HelloWorldMessage.class.getCanonicalName());
                    log.warn("Sending message");

                    return textMessage;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new JMSException(e.getMessage());
                }
            }
        });
        try {
            log.error("Response message: {}", receivedMessage.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
