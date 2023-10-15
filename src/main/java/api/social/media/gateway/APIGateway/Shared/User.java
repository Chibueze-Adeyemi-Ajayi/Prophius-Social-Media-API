package api.social.media.gateway.APIGateway.Shared;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
// import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

import api.social.media.gateway.APIGateway.Auth.Auth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "prophius_user", schema = "lynxng12_testDB", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String profile_picx;
    private int followers = 0;

    @JsonIgnore
    @OneToOne
    private Auth auth;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Post> post;

    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;
}
