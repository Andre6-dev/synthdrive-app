package io.devandre.synthdrive.bookings;

import io.devandre.synthdrive.customers.Customer;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import io.devandre.synthdrive.slots.Slot;
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
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "bookings")
@Builder
public class Booking extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingSequenceGenerator")
    @SequenceGenerator(name = "bookingSequenceGenerator", sequenceName = "booking_sequence", allocationSize = 1)
    private Long id;

    private BigDecimal pricePerHour;

    private BigDecimal totalPrice;

    private Timestamp startTime;

    private Timestamp endTime;

    private String vehicleNumber;

    private String phoneNumber;

    private String passcode;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id")
    private Slot slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Booking() {}

    public Booking(Long id, BigDecimal pricePerHour, BigDecimal totalPrice, Timestamp startTime, Timestamp endTime, String vehicleNumber, String phoneNumber, String passcode, BookingStatus status, Slot slot, Customer customer) {
        this.id = id;
        this.pricePerHour = pricePerHour;
        this.totalPrice = totalPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vehicleNumber = vehicleNumber;
        this.phoneNumber = phoneNumber;
        this.passcode = passcode;
        this.status = status;
        this.slot = slot;
        this.customer = customer;
    }

}
