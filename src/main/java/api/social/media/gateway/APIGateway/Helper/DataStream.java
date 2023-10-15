package api.social.media.gateway.APIGateway.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class DataStream {
    @Autowired
    Response response;
    public ResponseEntity <Response> parseResponse (String message, Object result, HttpStatus status) {
        response.setMessage(message);
        response.setResult(result);
        response.setStatus(status);
        return new ResponseEntity<Response>(response, null, status);
    }
}
