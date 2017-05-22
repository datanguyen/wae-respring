package wae.thesis.indiv.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wae.thesis.indiv.app.user.UserSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 *
 */
@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @RequestMapping(method = RequestMethod.GET)
    public String error(Model model) {
        Map<String, Object> appState = new HashMap<>();
        model.addAttribute("app", appState);
        appState.put("authenticate", new UserSession());
        return "app";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
