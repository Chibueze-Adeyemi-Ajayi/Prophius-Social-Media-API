package api.social.media.gateway.APIGateway.Systems;

// import java.util.List;
// import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import api.social.media.gateway.APIGateway.InterServiceRequestTemplate;
import api.social.media.gateway.APIGateway.Helper.JwtUtils;
import api.social.media.gateway.APIGateway.Helper.Request;
import api.social.media.gateway.APIGateway.Helper.SlugGenerator;
// import api.social.media.gateway.APIGateway.Shared.Post;
// import api.social.media.gateway.APIGateway.Shared.Post;
import api.social.media.gateway.APIGateway.Shared.User;
import api.social.media.gateway.APIGateway.Shared.UserRepo;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MyServices implements MyInterfaces {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    InterServiceRequestTemplate requestTemplate;

    @Value("${architecture.url}")
    String URL;

    @Value("${architecture.post_url}")
    String POST_URL;

    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    @CircuitBreaker(name = "profile_update", fallbackMethod = "profileUpdateCircuitBreakerFallbackMethod")
    @Bulkhead(name = "profile_update", fallbackMethod = "profileUpdateBulkHeadFallbackMethod")
    public Object updateProfile(User user, String username) throws Exception {
        System.out.println(user.getProfile_picx());
        requestTemplate.setUser(user);
        HttpEntity <InterServiceRequestTemplate> data = Request.parse(requestTemplate);
        User updated_user = restTemplate.postForObject((URL + "/update/" + username) , data, User.class);
        return updated_user;
    }

    @Override
    @CircuitBreaker(name = "profile_update", fallbackMethod = "profileViewCircuitBreakerFallbackMethod")
    @Bulkhead(name = "profile_update", fallbackMethod = "profileViewBulkHeadFallbackMethod")
    public Object viewProfile(String username) throws Exception {
        HttpEntity <InterServiceRequestTemplate> data = Request.parse(requestTemplate);
        Object user = restTemplate.postForObject((URL + "/" + username) , data, User.class);
        return user;
    }

    public Object profileUpdateCircuitBreakerFallbackMethod (Throwable throwable) throws Exception {
        // do some other stuffs here
        throw new Exception("Unfortunate our [USER] service at the moment is not available, we working to fix it");
    }

    public Object profileUpdateBulkHeadFallbackMethod (Throwable throwable) throws Exception {
         // do some other stuffs here
        throw new Exception("Unfortunate our [USER] service at the moment is not available, we working to fix it");
    }

    public Object profileViewCircuitBreakerFallbackMethod (Throwable throwable) throws Exception {
         // do some other stuffs here
        throw new Exception("Unfortunate our [USER] service at the moment is not available, we working to fix it");
    }

    public Object profileViewBulkHeadFallbackMethod (Throwable throwable) throws Exception {
         // do some other stuffs here
        throw new Exception("Unfortunate our [USER] service at the moment is not available, we working to fix it");
    }

    @Override
    @CircuitBreaker(name = "add_post", fallbackMethod = "postCircultBreaker")
    @Bulkhead(name = "add_post", fallbackMethod = "postBulkHead")
    public Object createPost(PostRequest post, HttpServletRequest rqst) throws Exception {
        String username = this.jwtUtils.extractUsername(rqst.getHeader("Authorization").substring(7));
        User user = this.userRepo.findByUsername(username);
        String content = post.getContent();
        PostServerPayLoad mypost = new PostServerPayLoad(); 
        mypost.setContent(content);
        String extract = content.substring(0, 16),
               slug = SlugGenerator.generateSlug(extract);
        mypost.setSlug(slug);
        requestTemplate.setPost(mypost);
        requestTemplate.setUser(user);
        HttpEntity <InterServiceRequestTemplate> data = Request.parse(requestTemplate);
        Object created_post = restTemplate.postForObject((POST_URL + "/add"), data, Object.class);
        return created_post;
    }

    public Object postCircultBreaker (Throwable throwable) throws Exception {
         // do some other stuffs here
        throw new Exception("Unfortunate our [POST] service at the moment is not available, we working to fix it");
    }

    public Object postBulkHead (Throwable throwable) throws Exception {
         // do some other stuffs here
        throw new Exception("Unfortunate our [POST] service at the moment is not available, we working to fix it");
    }

    @Override
    @CircuitBreaker(name = "update_post", fallbackMethod = "postCircultBreaker")
    @Bulkhead(name = "update_post", fallbackMethod = "postBulkHead")
    public Object updatePost(PostRequest post, String slug) throws Exception {
        PostServerPayLoad payload = new PostServerPayLoad();
        payload.setContent(post.getContent());
        this.requestTemplate.setPost(payload);
        HttpEntity <InterServiceRequestTemplate> data = Request.parse(requestTemplate);
        Object updated_post = restTemplate.postForObject((POST_URL + "/update/" + slug), data, Object.class);
        if (updated_post == null) throw new Exception("Unable to find post");
        return updated_post;
    }

    @Override
    @CircuitBreaker(name = "delete_post", fallbackMethod = "postCircultBreaker")
    @Bulkhead(name = "delete_post", fallbackMethod = "postBulkHead")
    public Object deletePost(String slug) throws Exception {
        Object post = restTemplate.postForObject((POST_URL + "/delete/" + slug), null, Boolean.class);
        if (post == null) throw new Error("Error deleting post");
        if (!(boolean) post) throw new Exception("Unable to delete post");
        return post;
    }

    @Override
    @CircuitBreaker(name = "read_post", fallbackMethod = "postCircultBreaker")
    @Bulkhead(name = "read_post", fallbackMethod = "postBulkHead")
    public Object readPost(String slug) throws Exception {
        Object post = restTemplate.postForObject((POST_URL + "/" + slug), null, Object.class);
        if (post == null) throw new Exception("Unable to find post");
        return post;
    }

    
}
