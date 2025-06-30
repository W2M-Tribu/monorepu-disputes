package es.w2m.finance.disputes.w2mdisputes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CustomerDispute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String summary;

    @Column(name = "customer_comment", columnDefinition = "TEXT")
    private String description;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    private String loc;
    private String amount;
}
