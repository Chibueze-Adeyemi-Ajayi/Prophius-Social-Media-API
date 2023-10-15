package api.social.media.gateway.APIGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import api.social.media.gateway.APIGateway.Helper.DataStream;
import api.social.media.gateway.APIGateway.Helper.Response;

@RestController
public class ErrorHandler implements ErrorController {

    @Autowired
    DataStream dataStream;
    
    public String getErrorPath () {
        return "/error";
    }

    @GetMapping("/error")
    public ResponseEntity <Response> getErrorResponseGET (Exception e) {
        return dataStream.parseResponse(e.getLocalizedMessage(), e, HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/error")
    public ResponseEntity <Response> getErrorResponsePOST (Exception e) {
        return dataStream.parseResponse(e.getLocalizedMessage(), e, HttpStatus.EXPECTATION_FAILED);
    }

    @PutMapping("/error")
    public ResponseEntity <Response> getErrorResponsePUT (Exception e) {
        return dataStream.parseResponse(e.getLocalizedMessage(), e, HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/error")
    public ResponseEntity <Response> getErrorResponseDEL (Exception e) {
        return dataStream.parseResponse(e.getMessage(), e.getCause(), HttpStatus.EXPECTATION_FAILED);
    }

}
