package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
<<<<<<<< HEAD:w2m-disputes-back/src/main/java/es/w2m/finance/disputes/w2mdisputes/infrastructure/adapter/output/persistence/entity/DisputeRecordEntity.java
public class DisputeRecordEntity {
========
public class Dispute {
>>>>>>>> develop:w2m-disputes-back/src/main/java/es/w2m/finance/disputes/w2mdisputes/infrastructure/adapter/output/persistence/entity/Dispute.java

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manager; // GESTOR

    @Column(name = "opened_date")
    private LocalDate openedDate; // FECHA APERTURA DISPUTA

    private String jira; // JIRA

    private String bp;

    @Column(name = "client_name")
    private String clientName;

    private String loc;

    @Column(name = "dispute_status")
    private String disputeStatus;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "agency_reference")
    private String agencyReference;

    private String delegation;

    @Column(name = "invoice_amount")
    private Double invoiceAmount;

    private String currency;

    @Column(name = "client_amount")
    private Double clientAmount;

    @Column(name = "disputed_amount")
    private Double disputedAmount;

    @Column(name = "final_sale")
    private Double finalSale;

    @Column(name = "final_currency")
    private String finalCurrency;

    @Column(name = "client_comment", columnDefinition = "TEXT")
    private String clientComment;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "internal_comment", columnDefinition = "TEXT")
    private String internalComment;

    @Column(columnDefinition = "TEXT")
    private String category;

    @Column(name = "jira_handled_date")
    private LocalDate jiraHandledDate;

    @Column(name = "jira_closed_date")
    private LocalDate jiraClosedDate;

    @Column(name = "handled_by")
    private String handledBy;
}
