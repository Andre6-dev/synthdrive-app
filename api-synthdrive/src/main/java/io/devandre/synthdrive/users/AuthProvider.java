package io.devandre.synthdrive.users;

import io.devandre.synthdrive.users.enums.AuthProviderType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auth_providers")
public class AuthProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authProviderlineSequenceGenerator")
    @SequenceGenerator(name = "authProviderlineSequenceGenerator", sequenceName = "auth_provider_sequence", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthProviderType type;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public AuthProvider() {}

    public AuthProvider(Long id, AuthProviderType type) {
        this.id = id;
        this.type = type;
    }
}
