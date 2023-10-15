package api.social.media.gateway.APIGateway.Systems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostServerPayLoad {
    private String content;
    private String slug;
}
