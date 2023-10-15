package api.social.media.userserver.SocialMediaAPIUserServer.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.social.media.userserver.SocialMediaAPIUserServer.InterServiceRequestTemplate;

@Service
public class UserService implements UserInterface {

    @Autowired
    UserRepo userRepo;

    @Override
    public User addUser(InterServiceRequestTemplate data) throws Exception {
        System.out.println("=================Processing User===================");
        User user = data.getUser();
        if (user == null) return null;
        User existing_user = userRepo.findByUsername(user.getUsername());
        if (existing_user != null) throw new Exception("User already exists");
        userRepo.save(user);
        return userRepo.findByUsername(user.getUsername());
    }

    @Override
    public User getUser(InterServiceRequestTemplate data) throws Exception {
        System.out.println("=================Finding User===================");
        String username = data.getString();
        if (username == null) return null;
        System.out.println(username);
        return userRepo.findByUsername(username);
    }

    @Override
    public User updateProfile(InterServiceRequestTemplate user, String username) throws Exception {
        User virtual_user = user.getUser();
        String email = virtual_user.getEmail(),
               profile_picx = virtual_user.getProfile_picx();
        User real_user = userRepo.findByUsername(username);
        real_user.setEmail(email);
        real_user.setProfile_picx(profile_picx);
        userRepo.save(real_user);
        return userRepo.findByUsername(username);
    }

    @Override
    public User ViewProfile(String username) throws Exception {
        return userRepo.findByUsername(username);
    }
    
}
