package wae.thesis.indiv.service.user.external.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wae.thesis.indiv.api.model.ServiceInfo;
import wae.thesis.indiv.service.user.internal.dao.UserDAO;
import wae.thesis.indiv.service.user.internal.model.User;
import wae.thesis.indiv.service.user.internal.model.UserDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */
public class UserSignUpAction extends UserAction {

    private DBI dbi;
    private UserDAO userDAO;
    private final ObjectMapper jacksonMapper = new ObjectMapper();


    public UserSignUpAction(DBI dbi) {
        super();
        this.dbi = dbi;
        userDAO = this.dbi.onDemand(UserDAO.class);
    }

    @Override
    public Object handle(ServiceInfo serviceInfo) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<User> allUsers = userDAO.getAllUser();
        int currentId = Integer.valueOf(allUsers.get(allUsers.size() - 1).getId());
        String newId = String.valueOf(currentId + 1);

        Map<String, String> userParams = serviceInfo.getParams();
        UserDto dto = new UserDto(userParams.get("username"), userParams.get("password"), userParams.get("info"));
        userDAO.addUser(newId,
                dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()),
                "CUSTOMER",
                dto.getInfo()
                );

        return dto;
    }
}
