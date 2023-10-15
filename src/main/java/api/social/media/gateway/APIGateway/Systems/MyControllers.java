package api.social.media.gateway.APIGateway.Systems;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.social.media.gateway.APIGateway.Helper.DataStream;
// import api.social.media.gateway.APIGateway.Shared.Post;
import api.social.media.gateway.APIGateway.Shared.User;

@RestController
@RequestMapping("/api/v1")
public class MyControllers implements MyInterfaces {

    
    @Autowired
    DataStream dataStream;

    @Autowired
    MyServices services;

    @Override
    @PutMapping("/profile/{username}")
    public Object updateProfile(@RequestBody User user, @PathVariable String username) throws Exception {
        return dataStream.parseResponse("Profile update successful", services.updateProfile(user, username), HttpStatus.OK);
    }

    @Override
    @GetMapping("/profile/{username}")
    public Object viewProfile(@PathVariable String username) throws Exception {
         return dataStream.parseResponse("Profile fetch successful", services.viewProfile(username), HttpStatus.OK);
    }

    @Override
    @PostMapping("/post")
    public Object createPost(@RequestBody PostRequest post, HttpServletRequest request) throws Exception {
        return dataStream.parseResponse("Post added successful", services.createPost(post, request), HttpStatus.OK);
    }

    @Override
    @PutMapping("/post/{slug}")
    public Object updatePost(@RequestBody PostRequest post, @PathVariable String slug) throws Exception {
        return dataStream.parseResponse("Post update successful", services.updatePost(post, slug), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/post/{slug}")
    public Object deletePost(@PathVariable String slug) throws Exception {
        return dataStream.parseResponse("Post delete successful", services.deletePost(slug), HttpStatus.OK);
    }

    @Override
    @GetMapping("/post/{slug}")
    public Object readPost(@PathVariable String slug) throws Exception {
        return dataStream.parseResponse("Post fetch successful", services.readPost(slug), HttpStatus.OK);
    }
    
}
