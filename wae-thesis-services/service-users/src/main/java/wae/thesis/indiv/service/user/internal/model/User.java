package wae.thesis.indiv.service.user.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Nguyen Tan Dat.
 */

@AllArgsConstructor
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String roles;
    private String info;
}
