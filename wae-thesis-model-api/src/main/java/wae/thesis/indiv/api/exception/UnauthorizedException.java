package wae.thesis.indiv.api.exception;

import wae.thesis.indiv.api.item.ErrorCode;

/**
 * Created by Nguyen Tan Dat.
 */

public class UnauthorizedException extends CoreException {

    public UnauthorizedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
