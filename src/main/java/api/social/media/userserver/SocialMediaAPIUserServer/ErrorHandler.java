package api.social.media.userserver.SocialMediaAPIUserServer;

import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class ErrorHandler implements ErrorController {
     public String getErrorPath () {
        return "/error";
    }

    @GetMapping("/error")
    public Exception getErrorResponseGET (Exception e) {
        return e;
    }

    @PostMapping("/error")
    public String getErrorResponsePOST (Exception e) {
        return e.getLocalizedMessage();
    }

    @PutMapping("/error")
    public Exception getErrorResponsePUT (Exception e) {
        return e;
    }

    @DeleteMapping("/error")
    public Exception getErrorResponseDEL (Exception e) {
        return e;
    }
}
