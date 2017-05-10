package wae.thesis.indiv.api.exception;

import lombok.Getter;
import wae.thesis.indiv.api.item.ErrorCode;

/**
 * Created by Nguyen Tan Dat.
 */
public class CoreException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    public CoreException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
