package org.example.alvin.demo;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ByteBufferTest {

  @Data
  private static class User {
    private long id;
    private String name;
  }

  public static void main(String[] args) {
    User user = new User();
    long randomId = System.currentTimeMillis();
    user.setId(randomId);
    user.setName("Yue, Chao Alvin");
    log.info("Original user: {}", user);

    byte[] nameBytes = user.getName().getBytes(StandardCharsets.UTF_16);
    int stringLength = user.getName().length();
    int bytesLength = nameBytes.length;

    // serialize
    ByteBuffer byteBuffer = ByteBuffer.allocate(8 + 4 + bytesLength);
    byteBuffer.putLong(user.getId());

    //    byteBuffer.putInt(stringLength);
    byteBuffer.putInt(bytesLength);
    byteBuffer.put(nameBytes);

    byte[] message = byteBuffer.array();

    // deserialize
    ByteBuffer transformedBuffer = ByteBuffer.wrap(message);
    User transformedUser = new User();
    transformedUser.setId(transformedBuffer.getLong());
    byte[] nameBytes1 = new byte[transformedBuffer.getInt()];
    transformedBuffer.get(nameBytes1);
    String name1 = new String(nameBytes1, StandardCharsets.UTF_16);
    transformedUser.setName(name1);
    log.info("Transformed user: {}", transformedUser);
  }
}
