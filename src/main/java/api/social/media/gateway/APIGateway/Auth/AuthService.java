package api.social.media.gateway.APIGateway.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
// import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import api.social.media.gateway.APIGateway.InterServiceRequestTemplate;
import api.social.media.gateway.APIGateway.Helper.JwtUtils;
import api.social.media.gateway.APIGateway.Helper.Request;
import api.social.media.gateway.APIGateway.Shared.User;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

// import java.util.HashMap;

@Service
public class AuthService implements AuthInterface, UserDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    InterServiceRequestTemplate requestTemplate;

    @Value("${architecture.url}")
    String URL;

    @Autowired
    AuthRepo authRepo;

    @Autowired
    JwtUtils jwtUtils;

    private Auth findUserByUsername (String username) throws UsernameNotFoundException {
        return authRepo.findByUsername(username);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "authentication_circult_breaking", fallbackMethod = "signupFallbackMethod")
    @Bulkhead(name = "authentication_bulkhead", fallbackMethod = "signupBulkheadMethod")
    public Object signup(AuthRequest auth) throws Exception {

        String username = auth.getUsername(),
               password = new BCryptPasswordEncoder().encode(auth.getPassword());

        requestTemplate.setString(username);

        // checking username validity
        HttpEntity <InterServiceRequestTemplate> data = Request.parse(requestTemplate);
        User existing_user =  restTemplate.postForObject(URL , data, User.class);
        if (existing_user != null) throw new Exception("User already exists with username " + username);

        System.out.println("---------------------STACKING IN USER------------------------");

        User virtual_user = new User();
            virtual_user.setUsername(username);
        requestTemplate.setUser(virtual_user); 

        // adding the user
        Object user = restTemplate.postForObject(URL + "/add" , data, User.class);
        if (user == null) throw new Exception("Please try again !");

        String token = this.jwtUtils.generateToken(username);

        // setting up authentication && authentication mechanism
        Auth auth_ = new Auth();
        auth_.setUsername(username); auth_.setToken(token);
        auth_.setPassword(password); auth_.setUser((User)user);

        authRepo.save(auth_);

        return (Object) auth_;
    }

    public Object signupFallbackMethod (Throwable throwable) throws Exception {
        String msg = throwable.getMessage();
        if (msg.indexOf("User already exists") > -1) throw new Exception("User already exists with the same username");
        throw new Exception("Our [USER] service is under maintenance, please try again later");
    }

    public Object signupBulkheadMethod (Throwable throwable) throws Exception {
        System.out.println(throwable.getMessage());
        throw new Exception("Our [USER] service is under maintenance, please try again later");
    }

    public Object signinFallbackMethod (Throwable throwable) throws Exception {
        String msg = throwable.getMessage();
        if (msg.indexOf("User already exists") > -1) throw new Exception("User already exists with the same username");
        throw new Exception("Our [USER] service is under maintenance, please try again later");
    }

    public Object signinBulkheadMethod (Throwable throwable) throws Exception {
        System.out.println(throwable.getMessage());
        throw new Exception("Our [USER] service is under maintenance, please try again later");
    }

    @Override
    @Transactional
    // @CircuitBreaker(name = "authentication_signin_circult_breaking", fallbackMethod = "signinFallbackMethod")
    @Bulkhead(name = "authentication_signin_bulkhead", fallbackMethod = "signinBulkheadMethod")
    public Object signin(AuthRequest auth) throws Exception {
        String username = auth.getUsername();
        requestTemplate.setString(username);
        HttpEntity <InterServiceRequestTemplate> data = Request.parse(requestTemplate);
        User existing_user =  restTemplate.postForObject(URL , data, User.class);
        if (existing_user == null) throw new Exception("User not found with username " + username);

        Auth authentication = authRepo.findByUsername(username);
        String token = this.jwtUtils.generateToken(username);
        authentication.setToken(token);
        authentication.setUser(existing_user);
        authRepo.save(authentication);
        return authRepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findUserByUsername(username);
    }
    
}
