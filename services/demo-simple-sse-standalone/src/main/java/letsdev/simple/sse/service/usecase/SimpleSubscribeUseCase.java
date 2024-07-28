package letsdev.simple.sse.service.usecase;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SimpleSubscribeUseCase {
    SseEmitter subscribe(String id);
}
