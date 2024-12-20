package io.devandre.synthdrive.managers;

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
@Table(name = "managers")
@Builder
public class Manager extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "managerSequenceGenerator")
    @SequenceGenerator(name = "managerSequenceGenerator", sequenceName = "manager_sequence", allocationSize = 1)
    private Long id;

    private String displayName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Manager() {}

    public Manager(Long id, String displayName, User user, Company company) {
        this.id = id;
        this.displayName = displayName;
        this.user = user;
        this.company = company;
    }

}
