package io.devandre.synthdrive.bookings;

import io.devandre.synthdrive.managers.Manager;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
import io.devandre.synthdrive.valets.Valet;
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

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "booking_timelines")
@Builder
public class BookingTimeline {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingTimelineSequenceGenerator")
    @SequenceGenerator(name = "bookingTimelineSequenceGenerator", sequenceName = "booking_timeline_sequence", allocationSize = 1)
    private Long id;

    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "valet_id")
    private Valet valet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public BookingTimeline() {}

    public BookingTimeline(Long id, Timestamp timestamp, BookingStatus status, Booking booking, Valet valet, Manager manager) {
        this.id = id;
        this.timestamp = timestamp;
        this.status = status;
        this.booking = booking;
        this.valet = valet;
        this.manager = manager;
    }
}
