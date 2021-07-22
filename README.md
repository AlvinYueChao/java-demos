# JavaDemos

## Notes:

### JVM

### Multiple Thread
**1. Slf4j + Log4j2 for Java 11 project**
```xml
<dependencies>
    <!-- slf4j dependencies -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.30</version>
    </dependency>
    <!-- log4j2 dependencies -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-slf4j-impl</artifactId>
        <version>2.13.2</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.13.2</version>
    </dependency>
</dependencies>
```

### Netty TCP & UDP
协议栈功能设计
| 用户故事 | 任务分解 |
| :----- | :-----: |
| 基于Netty的NIO通信框架，提供高性能的异步通信能力 | N/A |
| 提供消息的编解码框架，可以实现POJO的序列化和反序列化 | N/A |
| 提供基于IP地址的白名单接入认证机制 | 新增/移除用户 通过配置文件，重启服务解决 |
| --- | 从Redis或者Zookeeper动态更新/读取用户权限，无需重启 |
| 链路的有效性检验机制 | N/A |
| 链路的断线重连机制 | N/A |