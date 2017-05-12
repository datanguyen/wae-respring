package wae.thesis.indiv.api.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
public enum Message {

    AUTHENTICATION_FAILED("Username or password is incorrect"),
    TOKEN_EXPIRED("Token is expired"),
    TOKEN_NOT_FOUND("Token is not found");

    @Getter
    private String message;
}
