package com.task.management.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic taskUpdatesTopic() {
        return new NewTopic("task-updates", 1, (short) 1);
    }
}
