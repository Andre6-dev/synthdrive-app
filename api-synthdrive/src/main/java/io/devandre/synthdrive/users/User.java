package io.devandre.synthdrive.users;

import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
public class User extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;

    private UUID publicId;

    private String firstName;

    private String lastName;

    private String email;

    private String passwordHash;

    private String address;

    private String avatarUrl;

    private Boolean isEnabled;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {}

    public User(Long id, UUID publicId, String firstName, String lastName, String email, String passwordHash, String address, String avatarUrl, Boolean isEnabled, Role role) {
        this.id = id;
        this.publicId = publicId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.isEnabled = isEnabled;
        this.role = role;
    }
}
