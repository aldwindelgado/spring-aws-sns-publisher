package com.aldwindelgado.github.springawssnspublisher.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aldwin Delgado on Mar 14, 2020
 */
@Configuration
public class SNSConfiguration {

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    // Create an instance of Amazon SNS w/ Async functionality
    @Bean(name = "amazonSNS", destroyMethod = "shutdown")
    public AmazonSNSAsync amazonSNSAsync() {
        return AmazonSNSAsyncClientBuilder
            .standard()
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .withRegion(awsRegion)
            .build();
    }

    // Create a bean for Notif Messaging Template, this would require the "AmazonSNS" bean
    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(
        AmazonSNSAsync amazonSNSAsync) {
        return new NotificationMessagingTemplate(amazonSNSAsync);
    }

}
