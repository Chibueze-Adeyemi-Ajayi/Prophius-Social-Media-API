package api.social.media.gateway.APIGateway.Shared;

import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User, Long> {
    public User findByUsername(String username);
}
