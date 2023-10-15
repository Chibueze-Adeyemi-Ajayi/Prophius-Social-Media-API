package api.social.media.userserver.SocialMediaAPIUserServer.User;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.social.media.userserver.SocialMediaAPIUserServer.InterServiceRequestTemplate;

@RestController
@RequestMapping("/api/v1/user")
public class UserController implements UserInterface {

    @Autowired
    UserService userService;

    @Override
    @PostMapping("/add")
    public User addUser(@RequestBody InterServiceRequestTemplate data) throws Exception {
        return userService.addUser(data);
    }

    @Override
    @PostMapping("")
    public User getUser(@RequestBody InterServiceRequestTemplate data) throws Exception {
        return userService.getUser(data);
    }

    @Override
    @PostMapping("/update/{username}")
    public User updateProfile(@RequestBody InterServiceRequestTemplate user, @PathVariable String username) throws Exception {
        return userService.updateProfile(user, username);
    }

    @Override
    @PostMapping("/{username}")
    public User ViewProfile(@PathVariable String username) throws Exception {
        return userService.ViewProfile(username);
    }
    
}
