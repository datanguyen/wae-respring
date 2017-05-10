package wae.thesis.indiv.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Nguyen Tan Dat.
 */

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = GET)
    public String index() {
//        return "redirect:/app/home";
        return "app";
//    }}
    }
}
