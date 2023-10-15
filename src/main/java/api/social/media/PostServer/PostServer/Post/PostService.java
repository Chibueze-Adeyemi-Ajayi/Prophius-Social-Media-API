package api.social.media.PostServer.PostServer.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.social.media.PostServer.PostServer.InterServiceRequestTemplate;
import api.social.media.PostServer.PostServer.Shared.User;

@Service
public class PostService implements PostInterface {

    @Autowired
    PostRepo postRepo;

    @Override
    public Object addPost(InterServiceRequestTemplate post) {
       PostRequest sent_post = post.getPost();
       User user = post.getUser();
       Post virtual_post = new Post();
       virtual_post.setContent(sent_post.getContent());
       virtual_post.setSlug(sent_post.getSlug());
       virtual_post.setUser(user);
       postRepo.save(virtual_post);
       return (Post) postRepo.findBySlug(sent_post.getSlug());
    }

    @Override
    public Object updatePost(InterServiceRequestTemplate post, String slug) {
        Post created_post = postRepo.findBySlug(slug);
        if (created_post == null) return null;
        created_post.setContent(post.getPost().getContent());
        return postRepo.save(created_post);
    }

    @Override
    public Object deletePost(String slug) {
        try {
         Post post = (Post) viewPost(slug);
         if (post == null) throw new Exception("Post does not exist");
         postRepo.delete(post);
         return true;
        } catch (Exception e) { return false; }
    }

    @Override
    public Object viewPost(String slug) {
        return postRepo.findBySlug(slug);
    }
    
}
