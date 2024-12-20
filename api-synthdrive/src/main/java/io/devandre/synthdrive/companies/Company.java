package io.devandre.synthdrive.companies;

import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companies")
@Builder
public class Company extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companySequenceGenerator")
    @SequenceGenerator(name = "companySequenceGenerator", sequenceName = "company_sequence", allocationSize = 1)
    private Long id;

    private String displayName;

    private String address;

    private String description;

    public Company() {
    }

    public Company(Long id, String displayName, String address, String description) {
        this.id = id;
        this.displayName = displayName;
        this.address = address;
        this.description = description;
    }


}
