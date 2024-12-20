package io.devandre.synthdrive.verifications;

import io.devandre.synthdrive.garages.Garage;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import io.devandre.synthdrive.users.Admin;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "verifications")
@Builder
public class Verification extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verificationSequenceGenerator")
    @SequenceGenerator(name = "verificationSequenceGenerator", sequenceName = "verification_sequence", allocationSize = 1)
    private Long id;

    private boolean verified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id", unique = true, nullable = false)
    private Garage garage;

    public Verification() {}

    public Verification(Long id, boolean verified, Admin admin, Garage garage) {
        this.id = id;
        this.verified = verified;
        this.admin = admin;
        this.garage = garage;
    }
}
