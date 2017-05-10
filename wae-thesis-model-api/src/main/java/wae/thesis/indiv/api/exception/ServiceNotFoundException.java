package wae.thesis.indiv.api.exception;

import wae.thesis.indiv.api.item.ErrorCode;

/**
 * Created by Nguyen Tan Dat.
 */

public class ServiceNotFoundException extends CoreException {

    public ServiceNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
