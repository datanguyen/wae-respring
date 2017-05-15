package wae.thesis.indiv.service.user.internal;

import wae.thesis.indiv.api.behavior.ServiceInitializer;
import wae.thesis.indiv.api.model.ServiceInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nguyen Tan Dat.
 */
public class UserInitializer implements ServiceInitializer {
    @Override
    public Map<String, Object> initialize(ServiceInfo serviceInfo) {
        Map<String, Object> test = new HashMap<>();

        test.put("fucking", "student");
        test.put("fuckings", "students");
        return test;
    }
}
