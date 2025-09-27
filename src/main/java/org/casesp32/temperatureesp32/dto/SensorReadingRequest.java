package org.casesp32.temperatureesp32.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SensorReadingRequest {

    @NotBlank(message = "deviceId es requerido")
    private String deviceId;

    @DecimalMin(value = "-40.0", message = "temperatureC mínimo -40.0")
    @DecimalMax(value = "125.0", message = "temperatureC máximo 125.0")
    private double temperatureC;

    @DecimalMin(value = "0.0", message = "humidity mínimo 0.0")
    @DecimalMax(value = "100.0", message = "humidity máximo 100.0")
    private double humidity;
}
