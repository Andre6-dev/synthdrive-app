package io.devandre.synthdrive.addresses;

import io.devandre.synthdrive.garages.Garage;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "address")
@Builder
public class Address extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequenceGenerator")
    @SequenceGenerator(name = "addressSequenceGenerator", sequenceName = "address_sequence", allocationSize = 1)
    private Long id;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id")
    private Garage garage;

    public Address() {}

    public Address(Long id, String address, BigDecimal latitude, BigDecimal longitude, Garage garage) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.garage = garage;
    }
}
