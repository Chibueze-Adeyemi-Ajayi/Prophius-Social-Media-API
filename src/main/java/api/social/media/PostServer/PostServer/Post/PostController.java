package api.social.media.PostServer.PostServer.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.social.media.PostServer.PostServer.InterServiceRequestTemplate;

@RestController
@RequestMapping("api/v1/post")
public class PostController implements PostInterface {

    @Autowired
    PostService postService;

    @Override
    @PostMapping("/add")
    public Object addPost(@RequestBody InterServiceRequestTemplate post) {
        System.out.println("------------Hola Hola---------------");
        return postService.addPost(post);
    }

    @Override
    @PostMapping("/update/{slug}")
    public Object updatePost(@RequestBody InterServiceRequestTemplate post, @PathVariable String slug) {
        return postService.updatePost(post, slug);
    }

    @Override
    @PostMapping("/delete/{slug}")
    public Object deletePost(@PathVariable String slug) {
        return postService.deletePost(slug);
    }

    @Override
    @PostMapping("/{slug}")
    public Object viewPost(@PathVariable String slug) {
        return postService.viewPost(slug);
    }
    
}
