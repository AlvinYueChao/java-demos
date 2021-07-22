# JavaDemos

## Notes:

### JVM

### Multiple Thread
**1. Slf4j + Log4j2 for Java 11 project**
```xml
<dependencies>
    <!-- slf4j dependencies -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.13.2</version>
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