package wae.thesis.indiv.api;


/**
 * Created by Nguyen Tan Dat.
 */

public class ApiMessages  {

    public static String invalidServiceDefKey(String key) {
        return String.format("The key %s is not match the service key pattern", key);
    }

    public static String invalidActionName(String actionName) {
        return String.format("The name %s is not match the action name pattern", actionName);
    }

    public static String actionHandlerNotFound(String actionName) {
        return String.format("There is no action handler for action %s", actionName);
    }

    public static String couldNotParseDataToObject(String objectName) {
        return String.format("Cannot parse to object with name %s", objectName);
    }

    public static String unsupportedRequestMethod(String requestMethod) {
        return String.format("Request method %s does not supported by the application", requestMethod);
    }

    public static String couldNotReadDataFromRequest() {
        return "The requested data is unreadable";
    }
}
