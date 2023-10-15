package api.social.media.gateway.APIGateway.Auth;

public interface AuthInterface {
    public Object signin (AuthRequest auth) throws Exception;
    public Object signup (AuthRequest auth) throws Exception;
}
