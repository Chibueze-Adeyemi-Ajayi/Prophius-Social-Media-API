package api.social.media.gateway.APIGateway.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.social.media.gateway.APIGateway.Helper.DataStream;
import api.social.media.gateway.APIGateway.Helper.Response;
// import api.social.media.gateway.APIGateway.Shared.User;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthInterface {

    @Autowired
    AuthService authService;

    @Autowired
    DataStream dataStream;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody AuthRequest auth) throws Exception {
        Object user = authService.signup(auth);
        return dataStream.parseResponse("Successfully added user", user, HttpStatus.OK);
    }

    @Override
    @PostMapping("/signin")
    public Object signin(@RequestBody AuthRequest auth) throws Exception {
        try {
            String username = auth.getUsername(), password = auth.getPassword();
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return dataStream.parseResponse("Successfully logged-in", authService.signin(auth), HttpStatus.OK);
        } catch (Exception e) {
            return dataStream.parseResponse("Error authenticationg user", e.getLocalizedMessage(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }
    
}
