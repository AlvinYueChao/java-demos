package org.example.alvin.demo.serialize;

import java.util.Map;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

public class CustomSerializer implements Serializer<DemoUser> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    Serializer.super.configure(configs, isKey);
  }

  @Override
  public byte[] serialize(String s, DemoUser demoUser) {
    return new byte[0];
  }

  @Override
  public byte[] serialize(String topic, Headers headers, DemoUser data) {
    return Serializer.super.serialize(topic, headers, data);
  }

  @Override
  public void close() {
    Serializer.super.close();
  }
}
