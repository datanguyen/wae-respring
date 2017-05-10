package wae.thesis.indiv.app.restapi;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import wae.thesis.indiv.api.exception.CoreException;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.model.Error;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Nguyen Tan Dat.
 */

@ControllerAdvice
@RestController
public class ExceptionApi {

    @ExceptionHandler(value = CoreException.class)
    public Object getExceptionData(HttpServletRequest request, CoreException e) {
        Class<? extends CoreException> exception = e.getClass();
        ErrorCode errorCode = e.getErrorCode();
        String requestPath = request.getRequestURI();
        String message = e.getMessage();

        return new Error(exception, errorCode, errorCode.getCode(), requestPath, message);
    }
}
