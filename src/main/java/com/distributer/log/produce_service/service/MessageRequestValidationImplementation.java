package com.distributer.log.produce_service.service;

import com.distributer.log.produce_service.dto.ExceptionTraceValidator;
import com.distributer.log.produce_service.dto.HttpTraceValidator;
import com.distributer.log.produce_service.dto.KafkaMessageBody;
import com.distributer.log.produce_service.dto.ServiceResponse;
import com.distributer.log.produce_service.enums.Events;
import com.distributer.log.produce_service.exception.ConditionalValidatorException;
import com.distributer.log.produce_service.exception.SendFailedException;
import com.distributer.log.produce_service.util.LogConstants;
import com.distributer.log.produce_service.validators.RequestConditionalValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageRequestValidationImplementation implements MessageRequestService {

    private final RequestConditionalValidatorService validatorService;
    private final KafkaTemplate<String, KafkaMessageBody> kafkaTemplate;
    private final MessageSource messageSource;

    @Override
    public ServiceResponse produceLog(KafkaMessageBody messageBody) {
        try {
            validateEventType(messageBody.getEventType(), messageBody);
            kafkaTemplate.send("LogIngestion", messageBody).whenComplete((result, ex) -> {
                log.error("Callback invoked");
                if (ex != null) {
                    log.info("Kafka message send failed ");
                }
            });
            return ServiceResponse.builder()
                    .error(false)
                    .message(messageSource.getMessage("kafka.message.production.success", null, Locale.ENGLISH))
                    .details(List.of())
                    .build();
        } catch (Exception ex) {
            log.error(LogConstants.ExceptionMsg.EXCEPTION_PREFIX, ex.getCause() + " " + ex.getMessage(), "produceMessage()");
            throw new SendFailedException("kafka.message.production.failed");
        }
    }

    private void validateEventType(Events eventType, KafkaMessageBody messageBody) {
        try {
            switch (eventType.toString()) {
                case "EXCEPTION":
                    ExceptionTraceValidator exceptionValidator = new ExceptionTraceValidator();
                    BeanUtils.copyProperties(messageBody, exceptionValidator);
                    validatorService.validateEventType(exceptionValidator);
                    break;
                case "APPLICATION_LOG":
                    log.info("Application log, safely produced..!");
                    break;
                case "API_REQUEST":
                    HttpTraceValidator httpTraceValidator = new HttpTraceValidator();
                    BeanUtils.copyProperties(messageBody, httpTraceValidator);
                    validatorService.validateHttpEventType(httpTraceValidator);
                    break;
                case "CUSTOM_EVENT":
                    log.info("Custom event, safely produced..!");
                    break;
                default:
                    log.info("Default event, safely produced..!");
            }
        } catch (Exception e) {
            log.error(LogConstants.ExceptionMsg.EXCEPTION_PREFIX, e.toString(), "validateEventType()");
            throw new ConditionalValidatorException(e);
        }
    }
}
