package io.devandre.synthdrive.slots;

import io.devandre.synthdrive.garages.Garage;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "slots")
@Builder
public class Slot extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slotSequenceGenerator")
    @SequenceGenerator(name = "slotSequenceGenerator", sequenceName = "slot_sequence", allocationSize = 1)
    private Long id;

    private String displayName;

    private BigDecimal pricePerHour;

    private Integer length;

    private Integer width;

    private Integer height;

    @Enumerated(EnumType.STRING)
    private SlotType type = SlotType.CAR;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id")
    private Garage garage;

    public Slot() {}

    public Slot(Long id, String displayName, BigDecimal pricePerHour, Integer length, Integer width, SlotType type, Garage garage) {
        this.id = id;
        this.displayName = displayName;
        this.pricePerHour = pricePerHour;
        this.length = length;
        this.width = width;
        this.type = type;
        this.garage = garage;
    }
}
