package com.maatkamp.example.materialize.aisproducer.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultKafkaConsumerFactoryAfterPropertiesSetConfiguration {
    // DefaultKafkaConsumerFactory

    private Environment environment;

    private DefaultKafkaConsumerFactory defaultKafkaConsumerFactory;

    public DefaultKafkaConsumerFactoryAfterPropertiesSetConfiguration(Environment environment, DefaultKafkaConsumerFactory defaultKafkaConsumerFactory) {
        this.environment = environment;
        this.defaultKafkaConsumerFactory = defaultKafkaConsumerFactory;
        defaultKafkaConsumerFactory.setConfigureDeserializers(false);
        log.info(ReflectionToStringBuilder.toString(defaultKafkaConsumerFactory));
    }

}
