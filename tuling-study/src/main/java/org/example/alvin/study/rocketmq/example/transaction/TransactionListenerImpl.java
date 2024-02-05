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
package org.example.alvin.study.rocketmq.example.transaction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

@Slf4j
public class TransactionListenerImpl implements TransactionListener {

  private final AtomicInteger transactionIndex = new AtomicInteger(0); // 事务状态记录
  private final ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

  // 执行本地事务
  @Override
  public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
    log.info("执行本地事务");
    int value = transactionIndex.getAndIncrement();
    int status = value % 3;
    localTrans.put(msg.getTransactionId(), status);
    return LocalTransactionState.UNKNOW; // 这里模拟的不进行步骤4
  }

  // 检查本地事务状态
  @Override
  public LocalTransactionState checkLocalTransaction(MessageExt msg) {
    Integer status = localTrans.get(msg.getTransactionId());
    if (null != status) {
      switch (status) {
        case 0:
          log.info("MQ检查消息【{}】事务状态【中间状态】", msg.getTransactionId());
          return LocalTransactionState.UNKNOW;
        case 2:
          log.info("MQ检查消息【{}}】事务状态【回滚状态】", msg.getTransactionId());
          return LocalTransactionState.ROLLBACK_MESSAGE;
        case 1:
        default:
          log.info("MQ检查消息【{}}】事务状态【提交状态】", msg.getTransactionId());
          return LocalTransactionState.COMMIT_MESSAGE;
      }
    }
    log.info("MQ检查消息【{}】事务状态【提交状态】", msg.getTransactionId());
    return LocalTransactionState.COMMIT_MESSAGE;
  }
}
