package letsdev.inquiry.exception;

import letsdev.exception.support.ErrorCode;
import org.springframework.http.HttpStatus;

public enum InquiryErrorCode implements ErrorCode {
    INQUIRY_NOT_FOUND("찾을 수 없는 문의입니다.", HttpStatus.NOT_FOUND),
    INQUIRY_QUERY_FORBIDDEN("해당 문의에 대한 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INQUIRY_UPDATE_FORBIDDEN("해당 문의에 대한 수정 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INQUIRY_DELETE_FORBIDDEN("해당 문의에 대한 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("문의 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    InquiryErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }


    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public InquiryException defaultException() {
        return new InquiryException(this);
    }

    @Override
    public InquiryException defaultException(Throwable cause) {
        return new InquiryException(this, cause);
    }
}
