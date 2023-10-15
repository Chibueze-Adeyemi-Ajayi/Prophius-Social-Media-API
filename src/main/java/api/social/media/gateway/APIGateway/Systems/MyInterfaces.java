package api.social.media.gateway.APIGateway.Systems;

import javax.servlet.http.HttpServletRequest;

// import api.social.media.gateway.APIGateway.Shared.Post;
import api.social.media.gateway.APIGateway.Shared.User;

public interface MyInterfaces {
    public Object updateProfile (User user, String username) throws Exception;
    public Object viewProfile (String username) throws Exception;
    public Object createPost (PostRequest post, HttpServletRequest rqst) throws Exception;
    public Object updatePost (PostRequest post, String slug) throws Exception;
    public Object deletePost (String slug) throws Exception;
    public Object readPost (String slug) throws Exception;
}
