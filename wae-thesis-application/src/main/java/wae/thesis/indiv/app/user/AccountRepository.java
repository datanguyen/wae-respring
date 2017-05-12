package wae.thesis.indiv.app.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nguyen Tan Dat.
 */

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    Account findAccountByUsername(String username);
}
