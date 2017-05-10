package wae.thesis.indiv.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import wae.thesis.indiv.api.ApiMessages;
import wae.thesis.indiv.api.exception.CoreException;
import wae.thesis.indiv.api.item.ActionType;
import wae.thesis.indiv.api.item.ErrorCode;

import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
public class ServiceInfo {
    private final String path;
    private final ActionType type;
    private final Map<String, String> params;
    private final String data;

    private ServiceInfo(String path, ActionType type, Map<String, String> params, String data) {
        this.path = path;
        this.type = type;
        this.params = params;
        this.data = data;
    }

    public static ServiceInfo fromGETRequest(String path, Map<String, String> params) {
        return new ServiceInfo(path, ActionType.READ, params, null);
    }

    public static ServiceInfo fromPOSTRequest(String path, String data, Map<String, String> params) {
        return new ServiceInfo(path, ActionType.CREATE, params, data);
    }

    public static ServiceInfo fromPUTRequest(String path, String data, Map<String, String> params) {
        return new ServiceInfo(path, ActionType.UPDATE, params, data);
    }

    public static ServiceInfo fromDELETERequest(String path, Map<String, String> params) {
        return new ServiceInfo(path, ActionType.DELETE, params, null);
    }

    public void setParam(String name, String value) {
        params.put(name, value);
    }

    public String getParam(String name, String defaultValue) {
        if (params == null || !params.containsKey(name)){
            return defaultValue;
        }

        return params.get(name);
    }


    public String[] getParamAsArray(String name) {
        String param = getParam(name, null);

        return isParamAvailable(name) ? param.split("\\s*,\\s*") : null;
    }

    public Long getParamAsLong(String name) {
        return isParamAvailable(name) ? Long.valueOf(params.get(name)) : null;
    }

    public Double getParamAsDouble(String name) {
        return isParamAvailable(name) ? Double.valueOf(params.get(name)) : null;
    }

    public int getParamAsInt(String name, int defaultValue) {
        String param = getParam(name, String.valueOf(defaultValue));

        return Integer.valueOf(param);
    }

    public boolean getParamAsBoolean(String name, boolean defaultValue) {
        String param = getParam(name, String.valueOf(defaultValue));

        return Boolean.valueOf(param);
    }

    private boolean isParamAvailable(String name) {
        return StringUtils.isNotEmpty(getParam(name, null));
    }

    public <T> T getData(Class<T> dataType) {
        String dataString = getData();

        if (StringUtils.isEmpty(dataString)) {
            return null;
        }

        ObjectMapper jacksonMapper = new ObjectMapper();

        try {
            return jacksonMapper.readValue(dataString, dataType);
        } catch (Exception e) {
            throw new CoreException(
                  ApiMessages.couldNotParseDataToObject(dataType.getSimpleName()),
                  ErrorCode.CORE_EXCEPTION);
        }
    }
}
