package org.casesp32.temperatureesp32.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.casesp32.temperatureesp32.dto.SensorReadingRequest;
import org.casesp32.temperatureesp32.dto.SensorReadingResponse;
import org.casesp32.temperatureesp32.realtime.SseBroadcaster;
import org.casesp32.temperatureesp32.service.SensorReadingService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/readings")
@RequiredArgsConstructor
public class ReadingController {

    private final SensorReadingService service;
    private final SseBroadcaster broadcaster;

    @PostMapping
    public ResponseEntity<SensorReadingResponse> create(@Valid @RequestBody SensorReadingRequest req) {
        var resp = service.save(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/latest")
    public List<SensorReadingResponse> latest(@RequestParam String deviceId,
                                              @RequestParam(defaultValue = "50") int limit) {
        return service.latest(deviceId, limit);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamAll() {
        return broadcaster.subscribe();
    }

    @GetMapping("/")
    public String health() {
        return "OK";
    }

}
