package wae.thesis.indiv.api.storage;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
public class DataSource {
    private final String dataSourceUrl;
    private final String dataSourceUsername;
    private final String dataSourcePassword;
    private final MysqlDataSource mysqlDataSource = new MysqlConnectionPoolDataSource();

    private DBI dbi;

    public DataSource(String dataSourceUrl, String dataSourceUsername, String dataSourcePassword) {
        this.dataSourceUrl = dataSourceUrl;
        this.dataSourceUsername = dataSourceUsername;
        this.dataSourcePassword = dataSourcePassword;

        mysqlDataSource.setURL(this.dataSourceUrl);
        mysqlDataSource.setUser(this.dataSourceUsername);
        mysqlDataSource.setPassword(this.dataSourcePassword);

        dbi = new DBI(getMysqlDataSource());
    }
}
