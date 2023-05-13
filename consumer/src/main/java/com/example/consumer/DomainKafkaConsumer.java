package com.example.consumer;

import com.example.schema.Domain;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {

  @Bean
  public Consumer<KStream<String, Domain>> domainService() {
    return kstream -> kstream.foreach((key, domain) -> {
      System.out.printf("Domain consumed[%s] is Dead[%s]%n", domain.getDomain(), domain.getIsDead());
    });
  }
}
