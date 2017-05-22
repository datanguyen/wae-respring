package wae.thesis.indiv.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wae.thesis.indiv.app.user.UserSession;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Nguyen Tan Dat.
 */

@Controller
public class IndexController {

    @RequestMapping(path = "/", method = GET)
    public String index(Model model) {
        Map<String, Object> appState = new HashMap<>();
        model.addAttribute("app", appState);
        appState.put("authenticate", new UserSession());
        return "app";
    }

    @RequestMapping(path = "/signin", method = {GET, POST, PUT, DELETE})
    public String signIn(Model model) {
        Map<String, Object> appState = new HashMap<>();
        model.addAttribute("app", appState);
        appState.put("authenticate", null);
        return "app";
    }

    @RequestMapping(path = "/signout", method = {GET, POST, PUT, DELETE})
    public String signOut() {
        return "app";
    }

    @RequestMapping(path = "/signup", method = {GET, POST, PUT, DELETE})
    public String signUp(Model model) {
        Map<String, Object> appState = new HashMap<>();
        model.addAttribute("app", appState);
        appState.put("authenticate", null);
        return "app";
    }
}
