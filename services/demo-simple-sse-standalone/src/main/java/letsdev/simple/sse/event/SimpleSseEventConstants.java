package letsdev.simple.sse.event;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public final class SimpleSseEventConstants {

    public static final SseEventBuilder CONNECTED_EVENT;

    static {
        Map<String, Object> data = new HashMap<>();
        data.put("content", "SSE 구독이 완료되었습니다.");
        data.put("time", Instant.now());

        CONNECTED_EVENT = SseEmitter.event()
                .id("sse-connected")
                .name("sse-connected")
                .data(data)
                .reconnectTime(60_000L);
    }

    private SimpleSseEventConstants() {}
}
