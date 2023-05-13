package com.example.producer;




import com.example.schema.Domain;
import com.example.schema.DomainList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {

    @Value("${avro.topic.name}")
    String kafkaTopic;

    private final KafkaTemplate<String, Domain> kafkaTemplate;

    DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl(String name) {

        Mono<DomainList> domainListMono = WebClient.create().get().uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=com").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(DomainList.class);

        domainListMono.subscribe(domainList -> {
            domainList.getDomains().forEach(domain -> {
                if (!domain.getIsDead()) {
                    kafkaTemplate.send(kafkaTopic, domain);
                    System.out.println("Domain message" + domain.getDomain());
                } else {
                    System.out.println("Dead Domain message" + domain.getDomain());
                }

            });
        });

    }
}
