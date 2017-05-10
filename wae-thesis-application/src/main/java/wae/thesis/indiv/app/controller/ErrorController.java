package wae.thesis.indiv.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nguyen Tan Dat.
 *
 */
@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @RequestMapping(method = RequestMethod.GET)
    public String error() {
        return "app";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
