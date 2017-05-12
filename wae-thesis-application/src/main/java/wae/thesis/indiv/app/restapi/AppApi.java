package wae.thesis.indiv.app.restapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wae.thesis.indiv.api.ApiMessages;
import wae.thesis.indiv.api.behavior.ServiceBehavior;
import wae.thesis.indiv.api.exception.CoreException;
import wae.thesis.indiv.api.exception.DataInvalidException;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.UserRole;
import wae.thesis.indiv.api.model.ServiceInfo;
import wae.thesis.indiv.core.plugins.PluginManager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Nguyen Tan Dat.
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class AppApi {
    private final PluginManager pluginManager;

    @Autowired
    public AppApi(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }


    @RequestMapping(path = "/{serviceId}", method = {GET, POST, PUT, DELETE})
    public Object getServiceData(@PathVariable String serviceId,
                                 @RequestParam Map<String, String> params,
                                 HttpServletRequest httpServletRequest) {

        return getServiceData(serviceId, null, null, params, httpServletRequest);
    }

    @RequestMapping(path = "/{serviceId}/{subServiceId}", method = {GET, POST, PUT, DELETE})
    public Object getServiceData(@PathVariable String serviceId,
                                 @PathVariable String subServiceId,
                                 @RequestParam Map<String, String> params,
                                 HttpServletRequest httpServletRequest) {

        return getServiceData(serviceId, subServiceId, null, params, httpServletRequest);
    }

    @RequestMapping(path = "/{serviceId}/{subServiceId}/{functionId}", method = {GET, POST, PUT, DELETE})
    public Object getServiceData(@PathVariable String serviceId,
                                 @PathVariable String subServiceId,
                                 @PathVariable String functionId,
                                 @RequestParam Map<String, String> params,
                                 HttpServletRequest httpServletRequest) {

        ServiceBehavior serviceBehavior = (ServiceBehavior) pluginManager.getService(ServiceBehavior.class);
        String path = serviceBehavior.buildPath(serviceId, subServiceId, functionId);
        ServiceInfo serviceInfo = getServiceInfo(path, params, httpServletRequest);

        return serviceBehavior.handlerAction(UserRole.GUEST, serviceInfo);
    }

    private ServiceInfo getServiceInfo(String path, Map<String, String> params, HttpServletRequest httpServletRequest) {
        ServiceInfo serviceInfo = null;
        String requestMethod = httpServletRequest.getMethod();

        switch (requestMethod) {
            case "GET":
                serviceInfo = ServiceInfo.fromGETRequest(path, params);
                break;

            case "POST":
                serviceInfo = ServiceInfo.fromPOSTRequest(path, getDataBody(httpServletRequest), params);
                break;

            case "PUT":
                serviceInfo = ServiceInfo.fromPUTRequest(path, getDataBody(httpServletRequest), params);
                break;

            case "DELETE":
                serviceInfo = ServiceInfo.fromDELETERequest(path, params);
                break;

            default:
                break;
        }

        if (serviceInfo == null) {
            throw new CoreException(
                  ApiMessages.unsupportedRequestMethod(requestMethod),
                  ErrorCode.CORE_EXCEPTION);
        }

        return serviceInfo;
    }

    private String getDataBody(HttpServletRequest request) {
        try {
            return request.getReader().lines()
                  .reduce("", (accumulator, actual) -> accumulator + actual);
        } catch (IOException e) {
            throw new DataInvalidException(
                  ApiMessages.couldNotReadDataFromRequest(),
                  ErrorCode.CORE_EXCEPTION);
        }

    }
}




