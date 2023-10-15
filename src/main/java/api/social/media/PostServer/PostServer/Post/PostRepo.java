package api.social.media.PostServer.PostServer.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository <Post, Long> {
    public Post findBySlug (String slug);
    public boolean deleteBySlug (String slug);
}
