package com.distributer.log.produce_service.service;

import com.distributer.log.produce_service.dto.KafkaMessageBody;
import com.distributer.log.produce_service.dto.ServiceResponse;

public interface MessageRequestService {
    public ServiceResponse produceLog(KafkaMessageBody request);
}
