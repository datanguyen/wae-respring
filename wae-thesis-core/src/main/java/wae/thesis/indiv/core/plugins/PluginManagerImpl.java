package wae.thesis.indiv.core.plugins;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;


/**
 * Created by Nguyen Tan Dat.
 *
 */
@Getter
public class PluginManagerImpl implements PluginManager {

    private final String serviceDefPatterns;
    private final String dataSourceUrl;
    private final String dataSourceUsername;
    private final String dataSourcePassword;

    @Setter
    private PluginInitializer pluginInitializer;

    public PluginManagerImpl(String serviceDefPatterns, String dataSourceUrl,
                             String dataSourceUsername, String dataSourcePassword) {
        this.serviceDefPatterns = serviceDefPatterns;
        this.dataSourceUrl = dataSourceUrl;
        this.dataSourceUsername = dataSourceUsername;
        this.dataSourcePassword = dataSourcePassword;
    }

    @PostConstruct
    private void initPlugin() {
        setPluginInitializer(new PluginInitializerImpl(getServiceDefPatterns(),
              getDataSourceUrl(), getDataSourceUsername(), getDataSourcePassword()));
    }

    @Override
    public Object getService(Class serviceClass) {
        return getPluginInitializer().getServiceBehaviors();
    }
}
