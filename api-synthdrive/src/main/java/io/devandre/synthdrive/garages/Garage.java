package io.devandre.synthdrive.garages;

import io.devandre.synthdrive.companies.Company;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "garages")
@Builder
public class Garage extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "garageSequenceGenerator")
    @SequenceGenerator(name = "garageSequenceGenerator", sequenceName = "garage_sequence", allocationSize = 1)
    private Long id;

    private String displayName;

    private String description;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Garage() {}

    public Garage(Long id, String displayName, String description, String image, Company company) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.image = image;
        this.company = company;
    }
}
