package wae.thesis.indiv.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import wae.thesis.indiv.api.exception.CoreException;
import wae.thesis.indiv.api.item.ErrorCode;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
@AllArgsConstructor
public class Error {
    private Class<? extends CoreException> exception;
    private ErrorCode code;
    private int status;
    private String path;
    private String message;
}
