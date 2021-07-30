package org.example.alvin.tool.kafka.producer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

@Slf4j
public class StringProducer {

  private static final String STOP_FLAG = "stop";

  public static void main(String[] args) {
    String topicName = "org.example.alvin.private.json.test";
    String userName = "admin";
    String password = "admin-secret";
    String jaasConfigFormat = "org.apache.kafka.common.security.scram.ScramLoginModule required username=%s password=%s;";
    String jaasConfig = String.format(jaasConfigFormat, userName, password);

    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.20.10:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
    properties.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
    properties.put(SaslConfigs.SASL_JAAS_CONFIG, jaasConfig);
    KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

    log.info("Please enter your message in line, type 'stop' to quit");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line;
    for (; ; ) {
      try {
        if ((line = reader.readLine()) != null) {
          if (StringUtils.equalsIgnoreCase(line, STOP_FLAG)) {
            break;
          } else {
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, String.valueOf(System.currentTimeMillis()), line);
            producer.send(record, ((recordMetadata, e) -> {
              if (e != null) {
                log.error("Caught exception when trying to publish data to kafka", e);
              } else {
                log.info("Published successfully. {}", recordMetadata);
              }
            }));
          }
        }
      } catch (IOException e) {
        log.error("Cannot read anymore from console. exit");
        break;
      }
    }
  }
}
