package api.social.media.PostServer.PostServer;

import org.springframework.stereotype.Component;

// import api.social.media.PostServer.PostServer.Post.Post;
import api.social.media.PostServer.PostServer.Post.PostRequest;
import api.social.media.PostServer.PostServer.Shared.CommentRequest;
import api.social.media.PostServer.PostServer.Shared.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class InterServiceRequestTemplate {
    private String string;
    private Object jsonObject;
    private User user;
    private PostRequest post;
    private CommentRequest comment;
}