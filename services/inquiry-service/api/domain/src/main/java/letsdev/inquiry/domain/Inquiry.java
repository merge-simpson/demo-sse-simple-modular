package letsdev.inquiry.domain;

import letsdev.inquiry.domain.status.InquiryStatus;
import letsdev.inquiry.domain.status.InquiryVisibility;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
public class Inquiry {
    @Getter
    private String id; // independent type to infrastructure or connectivity api.
    @Getter
    private String authorId;

    public String title;
    public String content;
    public String author;

    public InquiryStatus status;
    public InquiryVisibility visibility;

    public Instant createdAt;
    public Instant updatedAt;
}
