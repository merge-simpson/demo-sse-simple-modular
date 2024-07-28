package letsdev.inquiry.readmodel;

import letsdev.inquiry.domain.status.InquiryStatus;
import letsdev.inquiry.domain.status.InquiryVisibility;
import lombok.Builder;

import java.time.Instant;

public final class InquiryReadModel {
    private InquiryReadModel() {}

    @Builder
    public record InquiryListViewReadModel(
            String title,
            String author,
            InquiryStatus status,
            InquiryVisibility visibility
    ) {}

    @Builder
    public record InquiryDetailedViewReadModel(
            String id,
            String authorId,
            String title,
            String content,
            String author,
            InquiryStatus status,
            InquiryVisibility visibility,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
