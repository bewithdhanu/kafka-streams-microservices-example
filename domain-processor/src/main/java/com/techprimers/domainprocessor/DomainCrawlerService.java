package com.techprimers.domainprocessor;

import com.techprimers.domaincrawler.Domain;
import com.techprimers.domaincrawler.DomainList;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {

    private final String KAFKA_TOPIC = "my-domains";
    private final KafkaTemplate<String, Domain> kafkaTemplate;

    public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl(String name) {

        Mono<DomainList> domainListMono = WebClient.create().get().uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=com").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(DomainList.class);


        domainListMono.subscribe(domainList -> {
            domainList.getDomains().forEach(domain -> {
                if (!domain.isDead()) {
                    kafkaTemplate.send(KAFKA_TOPIC, domain);
                    System.out.println("Domain message" + domain.getDomain());
                } else {
                    System.out.println("Dead Domain message" + domain.getDomain());
                }

            });
        });

    }
}
