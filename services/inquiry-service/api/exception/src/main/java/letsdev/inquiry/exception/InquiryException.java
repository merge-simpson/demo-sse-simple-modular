package letsdev.inquiry.exception;

import letsdev.exception.support.CustomException;
import letsdev.exception.support.ErrorCode;

public class InquiryException extends CustomException {
    public InquiryException() {
        super();
    }

    public InquiryException(String message) {
        super(message);
    }

    public InquiryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InquiryException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InquiryException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
