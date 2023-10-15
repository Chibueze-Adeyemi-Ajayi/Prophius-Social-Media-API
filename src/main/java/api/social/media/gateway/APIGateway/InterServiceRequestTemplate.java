package api.social.media.gateway.APIGateway;

import org.springframework.stereotype.Component;

// import api.social.media.gateway.APIGateway.Shared.Post;
import api.social.media.gateway.APIGateway.Shared.User;
import api.social.media.gateway.APIGateway.Systems.PostServerPayLoad;
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
