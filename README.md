# logback
logback配置
# 按照时间触发滚动
<?xml version="1.0" encoding="utf-8" ?>
<configuration>

    <!-- 自定义颜色（文件底部有对应自定义颜色处理类的代码） -->
    <conversionRule conversionWord="custom" converterClass="com.example.demo.CustomLogColor"/>

    <!-- 定义日志路径 -->
    <property name="LOG_PATH" value="/usr/local/logs/logback/"/>

    <!-- 控制台输出 -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!-- 这里有输出请求进来带有的requestId，在日常开发中，多个系统互相调用
             单次调用带上一个唯一的requestId，便于排查错误的时候日志跟踪-->
            <pattern>%blue([requestId:%X{requestId:-requestId}]) %d{yyyy-MM-dd HH:mm:ss.SSS} %red([%thread])
                %custom(%5level) - %msg%n
            </pattern>
        </encoder>
    </appender>

    <!-- 输出INFO级别的日志，指定INFO级别日志输出文件的位置 -->
    <appender name="INFO_LOG" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--INFO日志文件的绝对路径-->
        <file>${LOG_PATH}logback-test-info.log</file>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>%blue([requestId:%X{requestId:-requestId}]) %d{yyyy-MM-dd HH:mm:ss.SSS} %red([%thread])
                %custom(%5level) - %msg%n
            </pattern>
        </encoder>
        <!--根据时间+文件大小滚动日志文件
        时间：精确到天，最大记录天数30天
        文件：文件大小控制在50MB -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_PATH}logback-test-info.%d{yyyy-MM-dd}-%i.logx</fileNamePattern>
            <maxHistory>30</maxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>50MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <!-- 指定过滤器，用于过滤INFO级别的日志 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!-- 输出ERROR级别的日志，指定INFO级别日志输出文件的位置 -->
    <appender name="ERROR_LOG" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_PATH}logback-test-error.log</file>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>%blue([requestId:%X{requestId:-requestId}]) %d{yyyy-MM-dd HH:mm:ss.SSS} %red([%thread])
                %custom(%5level) - %msg%n
            </pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_PATH}logback-test-error.%d{yyyy-MM-ddHHmm}-%i.logx</fileNamePattern>
            <maxHistory>30</maxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>50MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <root level="info">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="INFO_LOG"/>
        <appender-ref ref="ERROR_LOG"/>
    </root>

</configuration>
