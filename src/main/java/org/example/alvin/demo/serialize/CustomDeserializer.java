package org.example.alvin.demo.serialize;

import java.util.Map;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomDeserializer implements Deserializer<DemoUser> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    Deserializer.super.configure(configs, isKey);
  }

  @Override
  public DemoUser deserialize(String s, byte[] bytes) {
    return null;
  }

  @Override
  public DemoUser deserialize(String topic, Headers headers, byte[] data) {
    return Deserializer.super.deserialize(topic, headers, data);
  }

  @Override
  public void close() {
    Deserializer.super.close();
  }
}
