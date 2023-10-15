package api.social.media.PostServer.PostServer.Post;

import api.social.media.PostServer.PostServer.InterServiceRequestTemplate;

public interface PostInterface {
    public Object addPost(InterServiceRequestTemplate post);
    public Object updatePost(InterServiceRequestTemplate post, String slug);
    public Object deletePost(String slug);
    public Object viewPost(String slug);
}
