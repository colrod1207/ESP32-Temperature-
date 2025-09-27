package org.casesp32.temperatureesp32.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("sensor_readings")
@CompoundIndex( //con el indice compuesto Mongo no hace un Collscan, sino que crea un B-Tree (arbol balanceado)
        name = "device_time_idx",
        def = "{'deviceId': 1, 'createdDate': -1}" //ordenamiento por dispositivo y por fecha de creacion
)
public class SensorReading {
    @Id private String id;
    @Indexed private String deviceId;
    private double temperatureC;
    private double humidity;
    @CreatedDate
    private LocalDateTime createdDate;

    public SensorReading() {}
    public SensorReading(String deviceId, double temperatureC, double humidity) {
        this.deviceId = deviceId;
        this.temperatureC = temperatureC;
        this.humidity = humidity;
    }
}
