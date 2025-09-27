package org.casesp32.temperatureesp32.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorReadingResponse {
    private String id;
    private String deviceId;
    private double temperatureC;
    private double humidity;
    private LocalDateTime createdDate;
}
