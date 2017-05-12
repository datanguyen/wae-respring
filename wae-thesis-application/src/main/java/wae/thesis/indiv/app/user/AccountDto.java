package wae.thesis.indiv.app.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String username;
    private String password;
}
