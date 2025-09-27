package org.casesp32.temperatureesp32.realtime;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.casesp32.temperatureesp32.dto.SensorReadingResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class SseBroadcaster {

    private final List<SseEmitter> clients = new CopyOnWriteArrayList<>();

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(0L); // 0L = sin timeout
        clients.add(emitter);

        emitter.onCompletion(() -> clients.remove(emitter));
        emitter.onTimeout(() -> clients.remove(emitter));
        emitter.onError(e -> clients.remove(emitter));

        try { emitter.send(SseEmitter.event().comment("connected")); } catch (IOException ignored) {}
        return emitter;
    }

    public void send(SensorReadingResponse reading) {
        for (SseEmitter emitter : clients) {
            try {
                emitter.send(SseEmitter.event()
                        .name("reading")
                        .data(reading));
            } catch (Exception e) {
                // cliente caído: retirarlo
                clients.remove(emitter);
            }
        }
    }
}
