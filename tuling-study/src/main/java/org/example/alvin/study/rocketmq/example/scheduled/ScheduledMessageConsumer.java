package org.example.alvin.study.rocketmq.example.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.example.alvin.study.rocketmq.example.LocalConstant;

@Slf4j
public class ScheduledMessageConsumer {

  public static void main(String[] args) throws Exception {
    // 实例化消费者
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ScheduledConsumer");
    // 指定Namesrv地址信息.
    consumer.setNamesrvAddr(LocalConstant.NAME_SERVER_ADDR);
    // 订阅Topics
    consumer.subscribe("ScheduledTopic", "*");
    // 注册消息监听者
    consumer.registerMessageListener(
        (MessageListenerConcurrently)
            (messages, context) -> {
              for (MessageExt message : messages) {
                // Print approximate delay time period
                log.info(
                    "Receive message[msgId={}] {}ms later",
                    message.getMsgId(),
                    message.getStoreTimestamp() - message.getBornTimestamp());
              }
              return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
    // 启动消费者
    consumer.start();
  }
}
