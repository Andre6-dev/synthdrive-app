package io.devandre.synthdrive.customers;

import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import io.devandre.synthdrive.users.User;
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
@Table(name = "customers")
@Builder
public class Customer extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequenceGenerator")
    @SequenceGenerator(name = "customerSequenceGenerator", sequenceName = "customer_sequence", allocationSize = 1)
    private Long id;

    private String displayName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Customer() {}

    public Customer(Long id, String displayName, User user) {
        this.id = id;
        this.displayName = displayName;
        this.user = user;
    }
}
