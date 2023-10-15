package api.social.media.gateway.APIGateway.Auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository <Auth, Long> {
    Auth findByUsername (String username);
}
