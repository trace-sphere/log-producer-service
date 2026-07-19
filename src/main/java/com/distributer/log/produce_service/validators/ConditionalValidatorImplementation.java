package com.distributer.log.produce_service.validators;

import com.distributer.log.produce_service.dto.ExceptionTraceValidator;
import com.distributer.log.produce_service.dto.HttpTraceValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class ConditionalValidatorImplementation implements RequestConditionalValidatorService {

    @Override
    public void validateEventType(@Valid ExceptionTraceValidator exceptionTraceValidator) {
        log.info("Exception trace validated !");
    }

    @Override
    public void validateHttpEventType(@Valid HttpTraceValidator httpTraceValidator) {
        log.info("Http trace validated !");
    }
}
