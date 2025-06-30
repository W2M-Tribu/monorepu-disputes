package es.w2m.finance.disputes.w2mdisputes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SupplierDispute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bp;
    private String summary;
    private String description;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    private String loc;
    private String amount;

    @Column(name = "supplier_comment", columnDefinition = "TEXT")
    private String supplierComment;
}
