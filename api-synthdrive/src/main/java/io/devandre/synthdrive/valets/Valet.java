package io.devandre.synthdrive.valets;

import io.devandre.synthdrive.companies.Company;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import io.devandre.synthdrive.users.User;
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
@Table(name = "valets")
@Builder
public class Valet extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valetSequenceGenerator")
    @SequenceGenerator(name = "valetSequenceGenerator", sequenceName = "valet_sequence", allocationSize = 1)
    private Long id;

    private String displayName;

    private String licenseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Valet() {}

    public Valet(Long id, String displayName, String licenseId, Company company, User user) {
        this.id = id;
        this.displayName = displayName;
        this.licenseId = licenseId;
        this.company = company;
        this.user = user;
    }
}
