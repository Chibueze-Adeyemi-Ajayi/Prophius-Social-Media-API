package api.social.media.userserver.SocialMediaAPIUserServer.Shared;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class PostServerPayLoad {
    private String content;
    private String slug;
}
