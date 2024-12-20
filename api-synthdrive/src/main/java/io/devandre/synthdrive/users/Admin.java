package io.devandre.synthdrive.users;

import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admins")
@Builder
public class Admin extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adminSequenceGenerator")
    @SequenceGenerator(name = "adminSequenceGenerator", sequenceName = "admin_sequence", allocationSize = 1)
    private Long id;

    private String displayName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Admin() {}

    public Admin(Long id, String displayName, User user) {
        this.id = id;
        this.displayName = displayName;
        this.user = user;
    }
}
