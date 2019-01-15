package com.jc;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.jc.dto.SevenSendersMessage;

@Configuration
@EnableKafka
public class KafkaConfigureConsumer {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String kafkaServers;


    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value(value = "${spring.kafka.consumer.user}")
    private String user;

    @Value(value = "${spring.kafka.consumer.password}")
    private String password;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SevenSendersMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SevenSendersMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, SevenSendersMessage> consumerFactory() {
        Map<String, Object> props = getConsumerFactoryProperties();
        return new DefaultKafkaConsumerFactory<>(props);
    }

    private Map<String, Object> getConsumerFactoryProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, getJaasConfigurationString());
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        return props;
    }


    private String getJaasConfigurationString() {
        return String.format("%s required username=\"%s\" password=\"%s\";",
                PlainLoginModule.class.getCanonicalName(), user, password);
    }

}
