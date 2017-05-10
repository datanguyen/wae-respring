package wae.thesis.indiv.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import wae.thesis.indiv.core.plugins.PluginManager;
import wae.thesis.indiv.core.plugins.PluginManagerImpl;

/**
 * Created by Nguyen Tan Dat.
 */

@Configuration
@ComponentScan(basePackages = "wae.thesis.indiv.core.*")
public class AppConfig {

    @Value("${application.service.defs}")
    private String serviceDefsPatterns;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.data-username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.data-password}")
    private String dataSourcePassword;


    @Bean
    public PluginManager pluginManager() {
        return new PluginManagerImpl(serviceDefsPatterns, dataSourceUrl,
              dataSourceUsername, dataSourcePassword);
    }
}
