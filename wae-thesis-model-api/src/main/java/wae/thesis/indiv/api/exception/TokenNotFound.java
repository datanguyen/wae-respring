package wae.thesis.indiv.api.exception;

import wae.thesis.indiv.api.item.ErrorCode;

/**
 * Created by Nguyen Tan Dat.
 */
public class TokenNotFound extends CoreException {
    public TokenNotFound(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
