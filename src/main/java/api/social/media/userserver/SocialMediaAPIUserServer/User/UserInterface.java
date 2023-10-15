package api.social.media.userserver.SocialMediaAPIUserServer.User;

import api.social.media.userserver.SocialMediaAPIUserServer.InterServiceRequestTemplate;

public interface UserInterface {
    public User addUser(InterServiceRequestTemplate user) throws Exception ;
    public User getUser(InterServiceRequestTemplate username) throws Exception ;
    public User updateProfile(InterServiceRequestTemplate user, String username) throws Exception ;
    public User ViewProfile(String username) throws Exception ;
}
