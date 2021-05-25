package org.example.alvin.study.rocketmq.example.batch;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.example.alvin.study.rocketmq.example.LocalConstant;

@Slf4j
public class BatchComuser {

  public static void main(String[] args) throws Exception {
    // 实例化消息生产者,指定组名
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("BatchComsuer");
    // 指定Namesrv地址信息.
    consumer.setNamesrvAddr(LocalConstant.NAME_SERVER_ADDR);
    // 订阅Topic
    consumer.subscribe("BatchTest", "*");
    //负载均衡模式消费
    consumer.setMessageModel(MessageModel.CLUSTERING);
    // 注册回调函数，处理消息
    consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
      log.info("{} received new message: {}", Thread.currentThread().getName(), msgs);
      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    });
    //启动消息者
    consumer.start();
    log.info("Consumer Started");
  }
}
