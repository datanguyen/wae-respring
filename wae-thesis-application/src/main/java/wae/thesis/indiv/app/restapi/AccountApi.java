package wae.thesis.indiv.app.restapi;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Nguyen Tan Dat.
 */

@RestController
public class AccountApi {

    @RequestMapping(path = "/account", method = {GET, POST, PUT, DELETE})
    public Object getAccount() {
        return SecurityContextHolder
              .getContext().getAuthentication()
              .getPrincipal();
    }

    @RequestMapping(path = "/signin", method = {GET, POST, PUT, DELETE})
    public Object getSignIn() {
        return SecurityContextHolder
              .getContext().getAuthentication()
              .getPrincipal();
    }


}
