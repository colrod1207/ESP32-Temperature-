package org.casesp32.temperatureesp32.service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.casesp32.temperatureesp32.domain.SensorReading;
import org.casesp32.temperatureesp32.dto.SensorReadingRequest;
import org.casesp32.temperatureesp32.dto.SensorReadingResponse;
import org.casesp32.temperatureesp32.repository.SensorReadingRepository;
import org.casesp32.temperatureesp32.realtime.SseBroadcaster;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorReadingService {

    private final SensorReadingRepository repo;
    private final SseBroadcaster broadcaster;

    public SensorReadingResponse save(SensorReadingRequest req) {
        SensorReading entity = new SensorReading(
                req.getDeviceId(),
                req.getTemperatureC(),
                req.getHumidity()
        );

        if (entity.getCreatedDate() == null) {
            entity.setCreatedDate(LocalDateTime.now());
        }

        SensorReading saved = repo.save(entity);

        SensorReadingResponse resp = SensorReadingResponse.builder()
                .id(saved.getId())
                .deviceId(saved.getDeviceId())
                .temperatureC(saved.getTemperatureC())
                .humidity(saved.getHumidity())
                .createdDate(saved.getCreatedDate())
                .build();

        broadcaster.send(resp);

        return resp;
    }

    public List<SensorReadingResponse> latest(String deviceId, int limit) {
        var pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<SensorReading> list = repo.findByDeviceId(deviceId, pageable);

        return list.stream()
                .map(r -> SensorReadingResponse.builder()
                        .id(r.getId())
                        .deviceId(r.getDeviceId())
                        .temperatureC(r.getTemperatureC())
                        .humidity(r.getHumidity())
                        .createdDate(r.getCreatedDate())
                        .build())
                .toList();
    }
}
