package letsdev.simple.sse.service;

import letsdev.simple.sse.repository.DefaultSseEmitterRepository;
import letsdev.simple.sse.service.usecase.SimpleSubscribeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import static letsdev.simple.sse.event.SimpleSseEventConstants.CONNECTED_EVENT;

@Service
public class SimpleSubscribeService implements SimpleSubscribeUseCase {

    private final DefaultSseEmitterRepository sseEmitterRepository;

    public SimpleSubscribeService(DefaultSseEmitterRepository sseEmitterRepository) {
        this.sseEmitterRepository = sseEmitterRepository;
    }

    @Override
    public SseEmitter subscribe(String id) {
        // create emitter
        SseEmitter emitter = new SseEmitter(60_000L /* default: 30_000L [ms] */);

        // allocate initial functions
        emitter.onCompletion(() -> sseEmitterRepository.deleteById(id));
        emitter.onError(emitter::completeWithError);
        emitter.onTimeout(emitter::complete);

        // send anything to keep connection
        sentConnectedEvent(emitter);

        // save and response
        return sseEmitterRepository.save(id, emitter);
    }

    private void sentConnectedEvent(SseEmitter emitter) {
        try {
            emitter.send(CONNECTED_EVENT);
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
