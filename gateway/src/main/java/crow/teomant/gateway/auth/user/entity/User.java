package crow.teomant.gateway.auth.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active")
    private Integer active = 1;

    @Column(name = "locked")
    private boolean isLocked = false;

    @Column(name = "expired")
    private boolean isExpired = false;

    @Column(name = "enabled")
    private boolean isEnabled = true;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> role;

}