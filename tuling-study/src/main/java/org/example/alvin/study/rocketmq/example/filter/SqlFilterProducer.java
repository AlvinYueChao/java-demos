/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.alvin.study.rocketmq.example.filter;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.example.alvin.study.rocketmq.example.LocalConstant;

@Slf4j
public class SqlFilterProducer {

  public static void main(String[] args) throws Exception {

    DefaultMQProducer producer = new DefaultMQProducer("SqlFilterProducer");
    producer.setNamesrvAddr(LocalConstant.NAME_SERVER_ADDR);

    producer.start();

    String[] tags = new String[] {"TagA", "TagB", "TagC"};

    for (int i = 0; i < 10; i++) {
      Message msg =
          new Message(
              "SqlFilterTest",
              tags[i % tags.length],
              ("Hello RocketMQ " + i).getBytes(StandardCharsets.UTF_8));
      // 设置一些属性
      msg.putUserProperty("a", String.valueOf(i));

      SendResult sendResult = producer.send(msg);
      log.info("{}", sendResult);
    }

    producer.shutdown();
  }
}
