
# Kafka Avro Serialization with Spring - Microservices Setup

This repository contains a sample project that demonstrates how to use Kafka with Avro serialization using Spring Cloud Stream. The project consists of a producer microservice and a consumer microservice. The producer microservice fetches information from an external API and publishes messages to a Kafka topic in Avro format. The consumer microservice receives messages from the Kafka topic and logs them into the console.

## Prerequisites

Before running the project, make sure you have the following prerequisites installed:

-   **Option 1: Apache Kafka and Confluent Schema Registry**

    -   Java Development Kit (JDK)
    -   Apache Kafka
    -   Confluent Schema Registry
-   **Option 2: Confluent Cloud (https://confluent.cloud/)**

    -   Java Development Kit (JDK)
    -   Confluent Cloud account with a Kafka cluster and Schema Registry
    -   Confluent Cloud API key and secret

## Project Structure

The project is structured as follows:

-   `application.yml`: YAML configuration files for the producer and consumer microservice.
-   Other project files and directories.

## Getting Started

To run the microservices, follow these steps:

1.  Configure the Kafka and Avro properties in the `application.yml` files according to your setup.
2.  Start Apache Kafka and Confluent Schema Registry.
3.  Build and run the producer microservice.
4.  Build and run the consumer microservice.

## Producer Microservice

The producer microservice fetches information from an external API and publishes messages to a Kafka topic in Avro format.

### REST API

The producer microservice provides the following REST API:

-   `POST /swapi/films/{filmName}`: Generates messages from the external API for the specified film and publishes them to the Kafka topic.

    -   Example: `POST http://localhost:8080/swapi/films/star`

        This API endpoint will generate messages with name "star" and publish them to the Kafka topic.


### Configuration

In the `producer.yml` file, you can configure the following properties:

-   `spring.cloud.stream.bindings.consumer-in-0.destination`: Specifies the Kafka topic to which the producer publishes messages.
-   `spring.cloud.stream.kafka.bindings.consumer-in-0.consumer.configuration.key.deserializer`: Specifies the key deserializer for the consumer.
-   `spring.cloud.stream.kafka.bindings.consumer-in-0.consumer.configuration.value.deserializer`: Specifies the Avro deserializer for the consumer.
-   `spring.cloud.stream.kafka.binder.brokers`: Specifies the Kafka broker(s) address.

## Consumer Microservice

The consumer microservice receives messages from the Kafka topic and logs them into the console.

### Configuration

In the `consumer.yml` file, you can configure the following properties:

-   `spring.kafka.bootstrap-servers`: Specifies the Kafka broker(s) address for the consumer.
-   `spring.kafka.producer.keySerializer`: Specifies the key serializer for the producer.
-   `spring.kafka.producer.valueSerializer`: Specifies the Avro serializer for the producer.
-   `spring.kafka.producer.properties.schema.registry.url`: Specifies the URL of the Avro schema registry.
-   `avro.topic.name`: Specifies the name of the Kafka topic from which the consumer consumes messages.