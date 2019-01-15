package com.jc;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfigureProducer {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String kafkaServers;

    @Value(value = "${spring.kafka.consumer.user}")
    private String user;

    @Value(value = "${spring.kafka.consumer.password}")
    private String password;

    @Bean
    public ProducerFactory<String, Object> producerJsonFactory() {
        return new DefaultKafkaProducerFactory<>(producerJsonConfigs());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerJsonConfigs() {
        Map<String, Object> props = producerConfigs();
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        return props;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, getJaasConfigurationString());
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");

        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        return props;
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaJsonTemplate() {
        return new KafkaTemplate<String, Object>(producerJsonFactory());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaDefaultTemplate() {
        return new KafkaTemplate<String, String >(producerFactory());
    }

    private String getJaasConfigurationString() {
        return String.format("%s required username=\"%s\" password=\"%s\";",
                PlainLoginModule.class.getCanonicalName(), user, password);
    }
}
