package letsdev.simple.sse.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DefaultSseEmitterRepository {
    private final Map<String, SseEmitter> storage;

    public DefaultSseEmitterRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    public SseEmitter save(String id, SseEmitter emitter) {
        return storage.put(id, emitter);
    }

    public Optional<SseEmitter> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Collection<SseEmitter> findAll() {
        return storage.values();
    }

    public Map<String, SseEmitter> getStorage() {
        return Map.copyOf(storage);
    }

    public void deleteById(String id) {
        storage.remove(id);
    }
}
