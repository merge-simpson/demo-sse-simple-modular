package letsdev.simple.sse.service;

import letsdev.simple.sse.repository.DefaultSseEmitterRepository;
import letsdev.simple.sse.service.usecase.SimpleBroadcastUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SimpleBroadcastService implements SimpleBroadcastUseCase {

    private final DefaultSseEmitterRepository sseEmitterRepository;

    @Override
    public void broadcast(String id, Map<String, Object> data) {
        SseEmitter emitter = sseEmitterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // generate event
        SseEventBuilder event = SseEmitter.event()
                .id("simple")
                .name("simple")
                .data(data)
                .reconnectTime(60_000L);

        // send event
        try {
            emitter.send(event);
        } catch (IOException e) {
            String reason = switch (e.getCause()) {
                case SocketTimeoutException ignore -> "Timeout occurred while trying to send event.";
                case SocketException se -> STR."Connection is reset or closed: \{se.getMessage()}";
                default -> STR."\{e.getMessage()}\nCaused by:\s\{e.getCause().getMessage()} (\{e.getCause()})";
            };

            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    reason,
                    e
            );
        }
    }
}
