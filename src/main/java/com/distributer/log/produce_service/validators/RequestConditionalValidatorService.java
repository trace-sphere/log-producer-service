package com.distributer.log.produce_service.validators;

import com.distributer.log.produce_service.dto.ExceptionTraceValidator;
import com.distributer.log.produce_service.dto.HttpTraceValidator;
import jakarta.validation.Valid;


public interface RequestConditionalValidatorService {
    void validateEventType(@Valid ExceptionTraceValidator exceptionTraceValidator);
    void validateHttpEventType(@Valid HttpTraceValidator httpTraceValidator);
}
