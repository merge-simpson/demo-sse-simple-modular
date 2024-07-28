package letsdev.simple.sse.controller;

import jakarta.servlet.http.HttpServletResponse;
import letsdev.simple.sse.service.usecase.SimpleSubscribeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SimpleSubscribeController {

    private final SimpleSubscribeUseCase simpleSubscribeUseCase;

    // HTTP Method: GET
    // MediaType: TEXT_EVENT_STREAM_VALUE
    //  streaming
    //  meeting standard
    //  auto-reconnection
    @GetMapping(path = "/simple/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeForInquiry(
            @RequestParam String id,
            HttpServletResponse response
    ) {
        // 리버스 프록시에서 불필요한 버퍼링을 방지
        response.addHeader("X-Accel-Buffering", "no");

        // response 때 클라이언트 정보와 연계되는 것으로 추정
        return simpleSubscribeUseCase.subscribe(id);
    }
}
