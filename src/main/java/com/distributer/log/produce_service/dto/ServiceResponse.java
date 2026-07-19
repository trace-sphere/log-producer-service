package com.distributer.log.produce_service.dto;

import lombok.*;
import org.json.simple.JSONObject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class ServiceResponse {
    private boolean error;
    private String message;
    private List<JSONObject> details;
}
