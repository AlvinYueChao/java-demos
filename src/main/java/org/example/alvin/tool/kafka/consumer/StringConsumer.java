package org.example.alvin.tool.kafka.consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;

@Slf4j
public class StringConsumer {

  public static void main(String[] args) {
    manuallyCommitOffset_commitConsumedOffset();
//    manuallyCommitOffset_commitConsumedOffsetPlusOne();
  }


  private static void manuallyCommitOffset_commitConsumedOffset() {
    String topicName = "org.example.alvin.private.json.test";
    String userName = "admin";
    String password = "admin-secret";
    String jaasConfigFormat = "org.apache.kafka.common.security.scram.ScramLoginModule required username=%s password=%s;";
    String jaasConfig = String.format(jaasConfigFormat, userName, password);

    Properties properties = new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.20.10:9092");
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
    properties.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
    properties.put(SaslConfigs.SASL_JAAS_CONFIG, jaasConfig);
    properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "specific-client");
    properties.put(CommonClientConfigs.GROUP_ID_CONFIG, "test-group-14");
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5);

    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
    consumer.subscribe(Collections.singletonList(topicName));

    Map<TopicPartition, OffsetAndMetadata> partitionOffsets = new ConcurrentHashMap<>();
    for (; ; ) {
      AtomicInteger accumulator = new AtomicInteger(0);
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
      for (ConsumerRecord<String, String> record : records) {
        log.info("Consumed message: {}, partition: {}, offset: {}", record.value(), record.partition(), record.offset());
        partitionOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset(), "no metadata"));
        int count = accumulator.incrementAndGet();
        if (count % 5 == 0) {
          consumer.commitSync(partitionOffsets);
          log.info("Committed offset, {}", consumer.committed(consumer.assignment()));
          partitionOffsets.keySet().forEach(topicPartition -> {
            long position = consumer.position(topicPartition);
            log.info("After committing, partition: {}, position: {}", topicPartition.partition(), position);
          });
        }
      }
    }
  }

  private static void manuallyCommitOffset_commitConsumedOffsetPlusOne() {
    String topicName = "org.example.alvin.private.json.test";
    String userName = "admin";
    String password = "admin-secret";
    String jaasConfigFormat = "org.apache.kafka.common.security.scram.ScramLoginModule required username=%s password=%s;";
    String jaasConfig = String.format(jaasConfigFormat, userName, password);

    Properties properties = new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.20.10:9092");
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
    properties.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
    properties.put(SaslConfigs.SASL_JAAS_CONFIG, jaasConfig);
    properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "specific-client");
    properties.put(CommonClientConfigs.GROUP_ID_CONFIG, "test-group-12");
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5);

    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
    consumer.subscribe(Collections.singletonList(topicName));

    Map<TopicPartition, OffsetAndMetadata> partitionOffsets = new ConcurrentHashMap<>();
    for (; ; ) {
      AtomicInteger accumulator = new AtomicInteger(0);
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
      for (ConsumerRecord<String, String> record : records) {
        log.info("Consumed message: {}, partition: {}, offset: {}", record.value(), record.partition(), record.offset());
        partitionOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1, "no metadata"));
        int count = accumulator.incrementAndGet();
        if (count % 5 == 0) {
          consumer.commitSync(partitionOffsets);
          log.info("Committed offset, {}", consumer.committed(consumer.assignment()));
          partitionOffsets.keySet().forEach(topicPartition -> {
            long position = consumer.position(topicPartition);
            log.info("After committing, partition: {}, position: {}", topicPartition.partition(), position);
          });
        }
      }
    }
  }

  private static void autoCommitOffset() {

  }
}
