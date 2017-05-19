package wae.thesis.indiv.service.user.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Nguyen Tan Dat.
 */
@AllArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
    private String info;
}
