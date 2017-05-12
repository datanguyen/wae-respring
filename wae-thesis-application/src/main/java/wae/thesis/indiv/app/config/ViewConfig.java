package wae.thesis.indiv.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

/**
 * Created by Nguyen Tan Dat.
 */

@Configuration
public class ViewConfig extends WebMvcConfigurerAdapter {
    public static final String[] SCRIPTS = {
          "nashorn/ejs.min.js",
          "nashorn/polyfill.js",
          "nashorn/render.js"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
              .addResourceHandler("/resources/**")
              .addResourceLocations("classpath:/resources/");
    }

    @Bean
    public ViewResolver viewResolver() {
        ScriptTemplateViewResolver viewResolver = new ScriptTemplateViewResolver();
        viewResolver.setPrefix("template/");
        viewResolver.setSuffix(".ejs");

        return viewResolver;
    }

    @Bean
    public ScriptTemplateConfigurer viewConfigurer() {
        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");
        configurer.setScripts(SCRIPTS);
        configurer.setRenderFunction("render");
        configurer.setSharedEngine(false);

        return configurer;
    }
}
