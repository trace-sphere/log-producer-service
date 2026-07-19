package com.distributer.log.produce_service.dto;

import com.distributer.log.produce_service.enums.Events;
import com.distributer.log.produce_service.enums.LogLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class KafkaMessageBody {

    private String logId;

    @NotNull(message = "{message.timestamp.null}")
    private Instant timeStamp;

    private String traceId;

    @NotNull(message = "{message.service.name.null}")
    @NotBlank(message = "{message.service.name.blank}")
    @Size(max = 100, message = "{message.service.name.size}")
    private String serviceName;

    private String environment;

    private String host;

    private String instanceId;

    @NotNull(message = "{message.event.type.null}")
    private Events eventType;

    @NotNull(message = "{message.level.type.null}")
    private LogLevel level;

    private String thread;

    private String logger;

    @NotNull(message = "{message.message.null}")
    @NotBlank(message = "{message.message.blank}")
    @Size(max = 255, message = "{message.message.size}")
    private String message;

    private String spanId;

    private String method;

    private String path;

    private String status;

    private Integer durationMs;

    private String clientIp;

    private String userAgent;

    private String version;

    private String region;

    private String timeZone;

    private String exceptionId;

    private String exceptionClass;

    private String exceptionMessage;

    private String stackTrace;
}
