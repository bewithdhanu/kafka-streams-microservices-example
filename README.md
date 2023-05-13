
# Kafka Avro Serialization with Spring - Project README

This repository contains a sample project that demonstrates how to use Kafka with Avro serialization using Spring. The project consists of a producer service and a consumer service.

## Prerequisites

Before running the project, make sure you have the following prerequisites installed:

-   Java Development Kit (JDK)
-   Apache Kafka
-   Confluent Schema Registry

## Project Structure

The project is structured as follows:

-   `producer.yml`: YAML configuration file for the producer service.
-   `consumer.yml`: YAML configuration file for the consumer service.
-   Other project files and directories.

## Getting Started

To run the project, follow these steps:

1.  Configure the Kafka and Avro properties in the `producer.yml` and `consumer.yml` files according to your setup.
2.  Start Apache Kafka and Confluent Schema Registry.
3.  Build and run the producer service.
4.  Build and run the consumer service.

## Producer Service

The producer service fetches information from a source and publishes it to a Kafka topic using Avro serialization.

### Configuration

In the `producer.yml` file, you can configure the following properties:

-   `spring.cloud.stream.bindings.consumer-in-0.destination`: Specifies the Kafka topic to which the producer publishes messages.
-   `spring.cloud.stream.kafka.bindings.consumer-in-0.consumer.configuration.key.deserializer`: Specifies the key deserializer for the consumer.
-   `spring.cloud.stream.kafka.bindings.consumer-in-0.consumer.configuration.value.deserializer`: Specifies the Avro deserializer for the consumer.
-   `spring.cloud.stream.kafka.binder.brokers`: Specifies the Kafka broker(s) address.

## Consumer Service

The consumer service consumes messages from the Kafka topic and processes them.

### Configuration

In the `consumer.yml` file, you can configure the following properties:

-   `spring.kafka.bootstrap-servers`: Specifies the Kafka broker(s) address for the consumer.
-   `spring.kafka.producer.keySerializer`: Specifies the key serializer for the producer.
-   `spring.kafka.producer.valueSerializer`: Specifies the Avro serializer for the producer.
-   `spring.kafka.producer.properties.schema.registry.url`: Specifies the URL of the Avro schema registry.
-   `avro.topic.name`: Specifies the name of the Kafka topic from which the consumer consumes messages.