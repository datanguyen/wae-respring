package wae.thesis.indiv.service.user.external.action;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import wae.thesis.indiv.api.model.ServiceInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */
public class UserSignUpAction extends UserAction {

    private DBI dbi;

    public UserSignUpAction(DBI dbi) {
        super();
        this.dbi = dbi;
    }

    @Override
    public Object handle(ServiceInfo serviceInfo) {
        String sql = "SELECT * from ecmrproj.account";
        Handle handle = null;
        List<Map<String, Object>> data = new ArrayList<>();

        try {
            handle = dbi.open();
            Query<Map<String, Object>> q = handle.createQuery(sql);
            data = q.list();

        } finally {
            handle.close();
        }

        return data;
    }
}
