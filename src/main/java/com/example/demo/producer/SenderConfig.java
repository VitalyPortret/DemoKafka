package com.example.demo.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SenderConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

//    public void init (String[] args) throws Exception {
//        if(args.length < 2){
//            System.out.println("Usage: consumer <topic> <groupname>");
//            return;
//        }
//
//        String topic = args[0].toString();
//        String group = args[1].toString();
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("group.id", group);
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("session.timeout.ms", "30000");
//        props.put("key.deserializer",
//                "org.apache.kafka.common.serializa-tion.StringDeserializer");
//        props.put("value.deserializer",
//                "org.apache.kafka.common.serializa-tion.StringDeserializer");
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
//
//        consumer.subscribe(Arrays.asList(topic));
//        System.out.println("Subscribed to topic " + topic);
//        int i = 0;
//
//        while (true) {
//            ConsumerRecords<String, String> records = con-sumer.poll(100);
//            for (ConsumerRecord<String, String> record : records)
//                System.out.printf("offset = %d, key = %s, value = %s\n",
//                        record.offset(), record.key(), record.value());
//        }
//    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
