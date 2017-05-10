package wae.thesis.indiv.core.plugins;

/**
 * Created by Nguyen Tan Dat.
 */

@FunctionalInterface
public interface PluginManager {
    Object getService(Class serviceClass);
}
