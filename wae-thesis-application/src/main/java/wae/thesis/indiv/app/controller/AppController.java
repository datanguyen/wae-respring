package wae.thesis.indiv.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wae.thesis.indiv.api.behavior.ServiceBehavior;
import wae.thesis.indiv.api.model.ServiceDef;
import wae.thesis.indiv.api.model.ServiceInfo;
import wae.thesis.indiv.core.plugins.PluginManager;
import wae.thesis.indiv.core.plugins.PluginManagerImpl;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Nguyen Tan Dat.
 */

@Controller
@RequestMapping("/app")
public class AppController {
    private final PluginManager pluginManager;

    @Autowired
    public AppController(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }


    @RequestMapping(path = "/{moduleId}")
    public String handleModuleRequest(@PathVariable String moduleId,
                                      @RequestParam Map<String, String> params,
                                      Model model) {
        return handleModuleRequest(moduleId, null, null, params, model);
    }

    @RequestMapping(path = "/{moduleId}/{subModuleId}", method = {GET, POST, PUT, DELETE})
    public String handleModuleRequest(@PathVariable String moduleId,
                                      @PathVariable String subModuleId,
                                      @RequestParam Map<String, String> params,
                                      Model model) {
        return handleModuleRequest(moduleId, subModuleId, null, params, model);
    }

    @RequestMapping(path = "/{moduleId}/{subModuleId}/{functionId}", method = {GET, POST, PUT, DELETE})
    public String handleModuleRequest(@PathVariable String moduleId,
                                      @PathVariable String subModuleId,
                                      @PathVariable String functionId,
                                      @RequestParam Map<String, String> params,
                                      Model model) {

        Map<String, Object> appState = new HashMap<>();
        model.addAttribute("app", appState);

        ServiceBehavior serviceBehavior = (ServiceBehavior) pluginManager.getService(ServiceBehavior.class);
        String path = serviceBehavior.buildPath(moduleId, subModuleId, functionId);
        ServiceInfo serviceInfo = ServiceInfo.fromGETRequest(path, params);

        appState.put("moduleId", moduleId);
        appState.put("subModuleId", subModuleId);
        appState.put("functionId", functionId);
        appState.put("path", path);
        appState.put("data", serviceBehavior.initService(serviceInfo));

        return "app";
    }
}
