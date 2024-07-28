package letsdev.simple.sse.controller;

import letsdev.simple.sse.controller.dto.SimpleSsePublishingRequest;
import letsdev.simple.sse.service.usecase.SimpleBroadcastUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimplePublishController {
    private final SimpleBroadcastUseCase simpleBroadcastUseCase;

    @PostMapping("/simple/events")
    public void broadcast(@RequestBody SimpleSsePublishingRequest body) {
        simpleBroadcastUseCase.broadcast(body.id(), body.data());
    }
}
