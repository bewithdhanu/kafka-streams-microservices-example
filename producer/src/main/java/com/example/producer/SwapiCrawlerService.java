package com.example.producer;


import com.example.schema.Film;
import com.example.schema.FilmPaginate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class SwapiCrawlerService {

    private final KafkaTemplate<String, Film> kafkaTemplate;
    @Value("${avro.topic.name}")
    String kafkaTopic;

    SwapiCrawlerService(KafkaTemplate<String, Film> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl(String name) {

        Mono<FilmPaginate> domainListMono = WebClient.create().get().uri("https://swapi.dev/api/films?search=" + name).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(FilmPaginate.class);

        domainListMono.subscribe(filmPaginate -> {
            if (filmPaginate.getResults().size() > 0) {
                filmPaginate.getResults().forEach(film -> {
                    kafkaTemplate.send(kafkaTopic, String.valueOf(new Date().getTime()), film);
                    System.out.printf("Film name %s%n", film.getTitle());
                });
            } else {
                System.out.println("No films available");
            }
        });

    }
}
