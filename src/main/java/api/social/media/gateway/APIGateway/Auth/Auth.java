package api.social.media.gateway.APIGateway.Auth;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import api.social.media.gateway.APIGateway.Shared.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prophius_auth", schema = "lynxng12_testDB", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "token")
})
public class Auth implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, name = "username")
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "token")
    private String token;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;
    private boolean active = true;

    @JsonManagedReference
    @OneToOne(mappedBy = "auth")
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(authority);
    }
    @Override
    public boolean isAccountNonExpired() {
       return this.isAccountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
       return this.isEnabled;
    }
}
