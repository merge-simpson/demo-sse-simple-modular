package letsdev.simple.sse.service.usecase;

import java.util.Map;

public interface SimpleBroadcastUseCase {
    void broadcast(String id, Map<String, Object> data);
}
