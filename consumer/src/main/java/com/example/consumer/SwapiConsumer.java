package com.example.consumer;


import com.example.schema.Film;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class SwapiConsumer {

    @KafkaListener(topics = "${avro.topic.name}")
    public void read(ConsumerRecord<String, Film> record) {
        Film film = record.value();
        System.out.printf("Film %s released by director %s\n", film.getTitle(), film.getDirector());
    }
}
