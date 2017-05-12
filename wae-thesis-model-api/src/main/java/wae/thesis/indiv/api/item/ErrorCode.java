package wae.thesis.indiv.api.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
public enum ErrorCode {

    CORE_EXCEPTION(500),
    SERVICE_NOT_FOUND(404),
    UNAUTHORIZED_PERMISSION(401),
    ACTION_NOT_FOUND(404),
    NO_ACTION_HANDLER(404),
    TOKEN_EXPIRED(500),
    TOKEN_NOT_FOUND(404),
    AUTHENTICATION_FAILED(403);

    @Getter
    private int code;
}
