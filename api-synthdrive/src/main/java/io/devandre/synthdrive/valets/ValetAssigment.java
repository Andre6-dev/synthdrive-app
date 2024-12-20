package io.devandre.synthdrive.valets;

import io.devandre.synthdrive.bookings.Booking;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "valets_assignments")
@Builder
public class ValetAssigment extends AbstractAuditingEntity<Long> {

    @Id
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "pickup_latitude", precision = 10, scale = 8)
    private BigDecimal pickupLatitude;

    @Column(name = "pickup_longitude", precision = 11, scale = 8)
    private BigDecimal pickupLongitude;

    @Column(name = "return_latitude", precision = 10, scale = 8)
    private BigDecimal returnLatitude;

    @Column(name = "return_longitude", precision = 11, scale = 8)
    private BigDecimal returnLongitude;

    @OneToOne
    @MapsId
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "pickup_valet_id")
    private Valet pickupValet;

    @ManyToOne
    @JoinColumn(name = "return_valet_id")
    private Valet returnValet;

    public ValetAssigment() {}

    public ValetAssigment(Long id, BigDecimal pickupLatitude, BigDecimal pickupLongitude, BigDecimal returnLatitude, BigDecimal returnLongitude, Booking booking, Valet pickupValet, Valet returnValet) {
        this.id = id;
        this.pickupLatitude = pickupLatitude;
        this.pickupLongitude = pickupLongitude;
        this.returnLatitude = returnLatitude;
        this.returnLongitude = returnLongitude;
        this.booking = booking;
        this.pickupValet = pickupValet;
        this.returnValet = returnValet;
    }
}
