package com.example.consumer;

import com.example.schema.Domain;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class DomainConsumer {

    @KafkaListener(topics = "${avro.topic.name}")
    public void read(ConsumerRecord<String, Domain> record) {
        Domain domain = record.value();
        System.out.printf("Domain consumed[%s] is Dead[%s]%n", domain.getDomain(), domain.getIsDead());
    }
}
