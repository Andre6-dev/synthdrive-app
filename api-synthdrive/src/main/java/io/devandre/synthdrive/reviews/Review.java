package io.devandre.synthdrive.reviews;

import io.devandre.synthdrive.customers.Customer;
import io.devandre.synthdrive.garages.Garage;
import io.devandre.synthdrive.shared.persistence.AbstractAuditingEntity;
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

@Getter
@Setter
@Entity
@Table(name = "reviews")
@Builder
public class Review extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewSequenceGenerator")
    @SequenceGenerator(name = "reviewSequenceGenerator", sequenceName = "review_sequence", allocationSize = 1)
    private Long id;

    private Integer rating;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id", nullable = false)
    private Garage garage;

    public Review() {}

    public Review(Long id, Integer rating, String comment, Customer customer, Garage garage) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.customer = customer;
        this.garage = garage;
    }
}
