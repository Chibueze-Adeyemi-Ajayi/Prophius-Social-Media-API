package api.social.media.gateway.APIGateway.Helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
// import org.springframework.stereotype.Component;

import api.social.media.gateway.APIGateway.InterServiceRequestTemplate;
// import lombok.AllArgsConstructor;

public class Request {
    @Value("${architecture.password}")
    static String password;
    HttpEntity <InterServiceRequestTemplate> httpEntity;
    static HttpHeaders headers = new HttpHeaders();
    public static HttpEntity<InterServiceRequestTemplate> parse (InterServiceRequestTemplate data) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        // encryption and decryption must be implmeneted here
        headers.add("token", "password");
        HttpEntity<InterServiceRequestTemplate> request = new HttpEntity<InterServiceRequestTemplate>(data, headers);
        return request;
    }
}
