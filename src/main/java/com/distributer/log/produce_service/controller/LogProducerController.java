package com.distributer.log.produce_service.controller;

import com.distributer.log.produce_service.dto.KafkaMessageBody;
import com.distributer.log.produce_service.dto.ServiceResponse;
import com.distributer.log.produce_service.service.MessageRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produce")
public class LogProducerController {

    private final MessageRequestService messageRequestService;

    @PostMapping("/send/logs")
    public ResponseEntity<ServiceResponse> consumeLogToProduce(@Valid @RequestBody KafkaMessageBody messageBody) {
        ServiceResponse response = messageRequestService.produceLog(messageBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
