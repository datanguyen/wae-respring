package wae.thesis.indiv.api;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

/**
 * Created by Nguyen Tan Dat.
 */


public class ApiFetcher {

    private final String apiPort;

    public ApiFetcher(String apiPort) {
        this.apiPort = apiPort;
    }

    public String fetch(String apiUrl) {
        HttpRequest request;
        HttpResponse<String> response;

        try {
            request = Unirest
                  .get(apiPort + apiUrl)
                  .getHttpRequest();

            response = request.asString();
        } catch (Exception e) {
            return null;
        }

        if (response.getStatus() < 200 || response.getStatus() > 299) {
            return null;
        }

        return response.getBody();
    }
}
