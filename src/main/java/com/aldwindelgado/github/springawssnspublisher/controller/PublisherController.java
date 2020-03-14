package com.aldwindelgado.github.springawssnspublisher.controller;

import com.aldwindelgado.github.springawssnspublisher.dto.SNSPublisherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aldwin Delgado on Mar 14, 2020
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/publish")
public class PublisherController {

    @Value("${app.sns.topic}")
    private String snsTopic;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @PostMapping
    public ResponseEntity<Void> publish(@RequestBody SNSPublisherDTO dto)
        throws JsonProcessingException {
        log.info("Publishing message {} to topic: {}", dto, snsTopic);

        ObjectMapper mapper = new ObjectMapper();

        /*
            Make sure that the JSON Object message will be converted to string so that the subscriber
            can deserialize the message properly into a Map<> object otherwise the subscriber handler
            will throw a "NULL POINTER EXCEPTION"...
         */
        notificationMessagingTemplate
            .sendNotification(snsTopic, mapper.writeValueAsString(dto.getMessage()),
                dto.getSubject());

        return ResponseEntity.ok().build();
    }


}
