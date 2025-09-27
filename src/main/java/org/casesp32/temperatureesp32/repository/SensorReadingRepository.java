package org.casesp32.temperatureesp32.repository;

import org.casesp32.temperatureesp32.domain.SensorReading;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SensorReadingRepository extends MongoRepository<SensorReading, String> {

    List<SensorReading> findByDeviceId(String deviceId, Pageable pageable);
}
