package wae.thesis.indiv.core;

import lombok.extern.slf4j.Slf4j;
import wae.thesis.indiv.api.exception.CoreException;
import wae.thesis.indiv.api.item.ErrorCode;
import wae.thesis.indiv.api.item.UserRole;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Nguyen Tan Dat.
 */

public class CoreMessages {
    private String propertyFileName;

    private final Map<String, String> messageByKey = new HashMap<>();

    public CoreMessages(String propertyFileName) {
        this.propertyFileName = propertyFileName;

        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.propertyFileName);

        try {
            properties.load(inputStream);
        } catch (CoreException | IOException e) {
            throw new CoreException("FILE_NOT_FOUND", ErrorCode.CORE_EXCEPTION);
        }

        Enumeration enumeration = properties.keys();
        while (enumeration.hasMoreElements()) {
            String key = String.valueOf(enumeration.nextElement());
            messageByKey.put(key, properties.getProperty(key));
        }
    }

    public String getMessage(String key) {
        return messageByKey.get(key);
    }

    public String noServiceForPath(String path) {
        return String.format(messageByKey.get("no.service.def.path"), path);
    }

    public String unauthorizedPermissionForRole(UserRole userRole) {
        return String.format(messageByKey.get("unauthorized.permission"), userRole);
    }

    public String noActionForPath(String path) {
        return String.format(messageByKey.get("no.action.path"), path);
    }

    public String noHandlerForAction(String actionName) {
        return String.format(messageByKey.get("no.handler.action"), actionName);
    }

    public String noServiceClassForName(String className) {
        return String.format(messageByKey.get("no.service.classname"), className);
    }
}
