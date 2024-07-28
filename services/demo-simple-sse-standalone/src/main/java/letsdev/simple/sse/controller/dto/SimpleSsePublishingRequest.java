package letsdev.simple.sse.controller.dto;

import java.util.Map;

public record SimpleSsePublishingRequest(
        // select one target for simple example.
        String id,
        // (map is not suggested) it's just for simple example.
        Map<String, Object> data
) {
}
