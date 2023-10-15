package api.social.media.userserver.SocialMediaAPIUserServer;

import org.springframework.stereotype.Component;

import api.social.media.userserver.SocialMediaAPIUserServer.Shared.PostServerPayLoad;
// import api.social.media.userserver.SocialMediaAPIUserServer.Shared.Post;
import api.social.media.userserver.SocialMediaAPIUserServer.User.User;
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
    private PostServerPayLoad post;
}
